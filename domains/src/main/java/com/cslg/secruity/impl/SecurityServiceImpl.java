package com.cslg.secruity.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.cslg.exception.AffairsException;
import com.cslg.secruity.SecurityService;
import com.cslg.secruity.repository.SecurityRepository;
import com.cslg.system.SysUserService;
import com.cslg.system.entity.SysUser;
import com.cslg.system.repository.SysMenuRepository;
import com.cslg.system.repository.SysRoleMenuRepository;
import com.cslg.system.vo.LoginVo;
import com.cslg.utils.MD5Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private SysUserService sysUserService;

    private SecurityRepository securityRepository;

    private SysRoleMenuRepository sysRoleMenuRepository;

    private SysMenuRepository sysMenuRepository;

    /**
     * 用户登录验证
     *
     * @param loginVo
     * @return
     */
    @Override
    public String securityService(LoginVo loginVo) {
        SysUser userByNameOrStuNo = sysUserService.getUserByNameOrStuNo(loginVo);
        if (userByNameOrStuNo.getStatus() == 0) {
            throw new AffairsException(2001, "用户已被禁用，请联系管理员解封!");
        }
        if (userByNameOrStuNo == null) {
            throw new AffairsException(2001, "用户不存在，请检查登录账号!");
        }
        if (MD5Utils.Encrypt(loginVo.getPassword()).equals(userByNameOrStuNo.getPassword())) {
            throw new AffairsException(2001, "密码不正确!");
        }
        StpUtil.login(userByNameOrStuNo.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String tokenValue = tokenInfo.getTokenValue();
        return tokenValue;
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
        map.put("name", sysUser.getName());
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return null;
    }
}
