package com.cslg.system.repository;

import com.cslg.system.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface SysMenuRepository {
    List<SysMenu> findAllMenu();

    void insertMenu(SysMenu sysMenu);

    void updateMenu(SysMenu sysMenu);

    void deleteMenuBtn(@Param("id") Long parentId);
}
