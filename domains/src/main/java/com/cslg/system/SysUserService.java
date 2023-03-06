package com.cslg.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cslg.system.entity.SysUser;
import com.cslg.system.param.PageUserCondition;
import com.cslg.system.repository.SysUserRepository;
import com.cslg.system.vo.LoginVo;
import com.cslg.vo.JsonPagedVO;

import java.util.List;

public interface SysUserService extends IService<SysUser> {
    JsonPagedVO<SysUser> pagedUserByCondition(PageUserCondition pageUserCondition);

    void insertOrUpdateUser(SysUser sysUser);

    void enableUser(SysUser sysUser);

    SysUser getUserById(Long id);

    void deleteUser(Long id);

    SysUser getUserByNameOrStuNo(LoginVo LoginVo);

    /**
     * 根据角色编码获取用户信息
     *
     * @param roleCode 角色编码
     * @return 返回用户信息
     */
    List<SysUser> getUserByRoleCode(String roleCode);

}
