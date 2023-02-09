package com.cslg.secruity.repository;

import com.cslg.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SecurityRepository {

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    SysUser getUserById(@Param("id") Long id);
}
