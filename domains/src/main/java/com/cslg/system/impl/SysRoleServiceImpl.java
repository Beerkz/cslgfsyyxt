package com.cslg.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cslg.system.SysRoleService;
import com.cslg.system.entity.SysRole;
import com.cslg.system.param.PageRoleCondition;
import com.cslg.system.repository.SysRoleRepository;
import com.cslg.vo.JsonPagedVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleRepository, SysRole> implements SysRoleService {
    private final SysRoleRepository sysRoleRepository;

    /**
     * 分页查询角色信息
     *
     * @param pageRoleCondition 分页条件
     * @return
     */
    @Override
    public JsonPagedVO<SysRole> pageRoleByCondition(PageRoleCondition pageRoleCondition) {
        final List<SysRole> sysRoles;
        final Long count = sysRoleRepository.countRoleByCondition(pageRoleCondition);
        if (count > 0) {
            sysRoles = sysRoleRepository.pageRoleByCondition(pageRoleCondition);
        } else {
            sysRoles = Collections.emptyList();
        }
        return new JsonPagedVO<>(sysRoles, count);
    }

    /**
     * 添加或修改角色
     *
     * @param sysRole 角色属性
     * @return Boolean
     */
    @Override
    public Boolean insertOrUpdateRole(SysRole sysRole) {
        //根据是否传递id判断是增加还是删除
        log.info("用户传递过来的id:{}", sysRole);
        Integer integer;
        if (sysRole.getId() != null) {
            integer = sysRoleRepository.insertRole(sysRole);

        } else {
            integer = sysRoleRepository.updateRole(sysRole);
        }

        if (integer != null && integer > 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
