package com.cslg.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cslg.system.SysUserService;
import com.cslg.system.entity.SysRole;
import com.cslg.system.entity.SysUser;
import com.cslg.system.param.PageUserCondition;
import com.cslg.system.repository.SysUserRepository;
import com.cslg.vo.JsonPagedVO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserRepository, SysUser> implements SysUserService {

    private final SysUserRepository sysUserRepository;

    /**
     * 角色分页查询
     *
     * @param pageUserCondition 分页查询条件
     * @return 分页返回封装类
     */
    @Override
    public JsonPagedVO<SysUser> pagedUserByCondition(PageUserCondition pageUserCondition) {
        log.info("查询条件:{}", pageUserCondition);
        final Long total = sysUserRepository.countUserByCondition(pageUserCondition);
        final List<SysUser> list;
        if (total > 0) {
            list = sysUserRepository.pagedUserByCondition(pageUserCondition);
        } else {
            list = Collections.emptyList();
        }
        return new JsonPagedVO<>(list, total);
    }

    /**
     * 添加或修改用户
     *
     * @param sysUser 用户实体类
     */
    @Override
    public void insertOrUpdateUser(SysUser sysUser) {
        final List<Long> ids;
        if (!sysUser.getRoleList().isEmpty()) {
            ids = sysUser.getRoleList().stream().map(SysRole::getId).collect(Collectors.toList());
        } else {
            ids = Collections.emptyList();
        }
        log.info("添加或修改用户信息:{}", ids);
        if (sysUser.getId() != null) {
            sysUserRepository.updateUser(sysUser);
            if (!ids.isEmpty()) {
                sysUserRepository.deleteUserRoleRelationship(sysUser.getId());
                sysUserRepository.insertUserRoleRelationship(sysUser.getId(), ids);
            }

        } else {
            sysUserRepository.insertUser(sysUser);
            if (!ids.isEmpty()) {
                sysUserRepository.insertUserRoleRelationship(sysUser.getId(), ids);
            }
        }
    }

    @Override
    public void enableUser(SysUser sysUser) {
        sysUserRepository.enableUser(sysUser);
    }

    /**
     * 根据id获取用户基本信息
     *
     * @param id
     */
    @Override
    public SysUser getUserById(Long id) {
        SysUser userById = sysUserRepository.getUserById(id);
        return userById;
    }

    /**
     * 删除用户信息
     *
     * @param id
     */
    @Override
    public void deleteUser(Long id) {
        sysUserRepository.deleteUser(id);
        sysUserRepository.deleteUserRoleRelationship(id);
    }
}
