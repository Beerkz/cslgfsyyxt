package com.cslg.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cslg.system.entity.SysRole;
import com.cslg.system.param.PageRoleCondition;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> pageRoleByCondition(PageRoleCondition pageRoleCondition);

    Boolean insertOrUpdateRole(SysRole sysRole);

}
