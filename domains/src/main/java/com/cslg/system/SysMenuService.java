package com.cslg.system;

import com.cslg.secruity.vo.RouterVo;
import com.cslg.system.entity.SysMenu;
import com.cslg.system.vo.AssignMenuVo;

import java.util.List;

public interface SysMenuService {
    List<SysMenu> findAllMenu();

    void saveOrUpdateMenu(SysMenu sysMenu);

    SysMenu findMenuById(Long id);

    void deleteMenuById(Long id);

    List<SysMenu> findMenuByRoleId(Long id);

    void doAssign(AssignMenuVo assignMenuVo);

    List<RouterVo> getUserMenuList(Long id);

    List<String> getUserButtonList(Long id);
}
