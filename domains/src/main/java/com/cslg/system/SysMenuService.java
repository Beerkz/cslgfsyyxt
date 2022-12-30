package com.cslg.system;

import com.cslg.system.entity.SysMenu;

import java.util.List;

public interface SysMenuService {
    List<SysMenu> findAllMenu();

    void saveOrUpdateMenu(SysMenu sysMenu);
}
