package com.cslg.system.repository;

import com.cslg.secruity.vo.RouterVo;
import com.cslg.system.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface SysMenuRepository {
    /**
     * 获取所有菜单 不包括按钮
     *
     * @return
     */
    List<SysMenu> findAllMenu();

    /**
     * 根据父级id 获取按钮名称
     *
     * @param id
     * @return
     */
    List<SysMenu> findAllBtn(@Param("id") Long id);

    /**
     * 增加菜单
     *
     * @param sysMenu
     */
    void insertMenu(SysMenu sysMenu);

    /**
     * 修改菜单
     *
     * @param sysMenu
     */
    void updateMenu(SysMenu sysMenu);

    /**
     * 根据父级id删除所有按钮
     *
     * @param parentId
     */
    void deleteMenuBtn(@Param("id") Long parentId);

    /**
     * 根据id查询菜单信息
     *
     * @param id
     * @return
     */
    SysMenu findMenuById(@Param("id") Long id);

    /**
     * 根据id删除菜单
     *
     * @param id
     */
    void deleteMenu(@Param("id") Long id);

    /**
     * 根据父级id获取菜单信息
     *
     * @param id
     * @return
     */
    List<SysMenu> findMenuByParentId(@Param("id") Long id);

    /**
     * 根据角色id获取菜单信息
     *
     * @param id
     * @return
     */
    List<SysMenu> findMenuByRoleId(@Param("id") Long id);

    /**
     * 查询所有按钮和菜单信息
     *
     * @return
     */
    List<SysMenu> findAllMenuAndBtn();

    /**
     * 根据用户id查询所有菜单
     */
    List<SysMenu> getUserMenuList(@Param("id") Long userId);

    /**
     * 根据用户id查询所有按钮权限
     */
    List<String> getUserButtonList(@Param("id") Long id);

}
