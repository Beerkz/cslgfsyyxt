package com.cslg.system.repository;

import com.cslg.system.vo.AssignMenuVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SysRoleMenuRepository {

    void deleteMenuByRoleId(@Param("id") Long id);

    void insertRoleMenu(@Param("roleId") Long roleId, @Param("items") List<Long> ids);
}
