package com.cslg.system.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cslg.system.entity.SysRole;
import com.cslg.system.param.PageRoleCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysRoleRepository extends BaseMapper<SysRole> {
    /**
     * 根据条件查询数量
     *
     * @param pageRoleCondition 分页条件
     * @return
     */
    Long countRoleByCondition(PageRoleCondition pageRoleCondition);

    /**
     * 分页查询角色信息
     *
     * @param pageRoleCondition 分页条件
     * @return
     */
    List<SysRole> pageRoleByCondition(PageRoleCondition pageRoleCondition);

    /**
     * 插入角色
     *
     * @param sysRole
     * @return
     */
    Integer insertRole(SysRole sysRole);

    /**
     * 更新角色
     *
     * @param sysRole
     * @return
     */
    Integer updateRole(SysRole sysRole);


}
