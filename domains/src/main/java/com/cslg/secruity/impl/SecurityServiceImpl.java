package com.cslg.secruity.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.cslg.exception.AffairsException;
import com.cslg.secruity.LogService;
import com.cslg.secruity.SecurityService;
import com.cslg.secruity.config.LogAspectConfig;
import com.cslg.secruity.entity.SysLoginLog;
import com.cslg.secruity.repository.SecurityRepository;
import com.cslg.system.SysMenuService;
import com.cslg.system.SysUserService;
import com.cslg.system.entity.SysUser;
import com.cslg.system.repository.SysMenuRepository;
import com.cslg.system.repository.SysRoleMenuRepository;
import com.cslg.system.vo.LoginVo;
import com.cslg.utils.MD5Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 用户登录服务类
 */
@Service
@AllArgsConstructor
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    private SysUserService sysUserService;

    private SecurityRepository securityRepository;

    private LogService logService;

    private SysMenuService sysMenuService;

    /**
     * 用户登录验证
     *
     * @param loginVo
     * @return
     */
    @Override
    public String securityService(LoginVo loginVo) {
        SysUser userByNameOrStuNo = sysUserService.getUserByNameOrStuNo(loginVo);
        if (userByNameOrStuNo == null) {
            getLoginLog(loginVo, 1, "用户不存在，请检查登录账号!");
            throw new AffairsException(2001, "用户不存在，请检查登录账号!");
        }
        if (userByNameOrStuNo.getStatus() == 1) {
            getLoginLog(loginVo, 1, "用户已被禁用，请联系管理员解封!");
            throw new AffairsException(2001, "用户已被禁用，请联系管理员解封!");
        }
        //if (MD5Utils.Encrypt(loginVo.getPassword()).equals(userByNameOrStuNo.getPassword())) {
        //getLoginLog(loginVo,1,"密码不正确!");
        //    throw new AffairsException(2001, "密码不正确!");
        //}
        getLoginLog(loginVo, 0, "登录成功");
        StpUtil.login(userByNameOrStuNo.getId());
        //satoken缓存用户信息
        StpUtil.getSession().set(String.valueOf(userByNameOrStuNo.getId()), userByNameOrStuNo);
        log.info("session中缓存的登陆对象信息:{}", StpUtil.getSession().get(String.valueOf(userByNameOrStuNo.getId())));
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String tokenValue = tokenInfo.getTokenValue();
        log.info("当前用户的登录token:{}", tokenValue);
        return tokenValue;
    }

    /**
     * 将登录信息转换为用户信息,插入数据库
     *
     * @param loginVo
     * @return
     */
    public void getLoginLog(LoginVo loginVo, Integer status, String msg) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        if (StringUtils.hasText(loginVo.getUsername())) {
            sysLoginLog.setUsername(loginVo.getUsername());
        } else {
            sysLoginLog.setUsername(loginVo.getStuNo());
        }
        sysLoginLog.setStatus(status);
        sysLoginLog.setIpaddr(LogAspectConfig.getIpAddress());
        sysLoginLog.setMsg(msg);
        log.info("登录日志信息:{}", sysLoginLog);
        logService.insertLoginLog(sysLoginLog);

    }


    /**
     * 获取用户信息
     * 根据用户id 获取用户信息（基本信息 和 菜单权限 和 按钮权限数据）
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getUserInfo(Long id) {
        SysUser sysUser = securityRepository.getUserById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "[" + sysUser.getName() + "]");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("menus", sysMenuService.getUserMenuList(id));
        map.put("introduction", "aaaa");
        map.put("buttons", sysMenuService.getUserButtonList(id));
        log.info("当前用户信息:{}", map);
        return map;
    }
}
