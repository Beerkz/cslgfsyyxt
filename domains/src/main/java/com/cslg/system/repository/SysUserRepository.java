package com.cslg.system.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cslg.system.entity.SysRole;
import com.cslg.system.entity.SysUser;
import com.cslg.system.param.PageUserCondition;
import com.cslg.system.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserRepository extends BaseMapper<SysUser> {
    List<SysUser> pagedUserByCondition(PageUserCondition pageUserCondition);

    Long countUserByCondition(PageUserCondition pageUserCondition);

    Long insertUser(SysUser sysUser);

    Long updateUser(SysUser sysUser);

    Long insertUserRoleRelationship(@Param("id") Long id, @Param("items") List<Long> items);

    Long deleteUserRoleRelationship(@Param("id") Long id);

    Long enableUser(SysUser sysUser);

    SysUser getUserById(@Param("id") Long id);

    List<SysRole> getUserRole(@Param("id") Long id);

    Long deleteUser(@Param("id") Long id);

    SysUser getUserByNameOrStuNo(LoginVo loginVo);

    List<SysUser> getUserByRoleCode(@Param("code") String code);

}
