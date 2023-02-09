package com.cslg.system.impl;

import com.cslg.system.SysMenuService;
import com.cslg.system.entity.SysMenu;
import com.cslg.system.repository.SysMenuRepository;
import com.cslg.system.repository.SysRoleMenuRepository;
import com.cslg.system.vo.AssignMenuVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuRepository sysMenuRepository;

    private final SysRoleMenuRepository sysRoleMenuRepository;

    @Override
    public List<SysMenu> findAllMenu() {
        List<SysMenu> allMenu = sysMenuRepository.findAllMenu();
        List<SysMenu> list = new ArrayList<>();
        allMenu.stream().forEach(s -> {
            if (s.getParentId() == 0) {
                SysMenu sysMenu = new SysMenu();
                BeanUtils.copyProperties(s, sysMenu);
                s.getChildren().clear();
                allMenu.stream().forEach(a -> {
                    if (a.getParentId() == sysMenu.getId()) {
                        sysMenu.getChildren().add(a);
                    }
                });
                list.add(sysMenu);
            }
        });
        return list;
    }

    @Override
    public void saveOrUpdateMenu(SysMenu sysMenu) {
        if (sysMenu.getId() != null) {
            sysMenuRepository.updateMenu(sysMenu);
            if (sysMenu.getChildren() != null && !sysMenu.getChildren().isEmpty()) {
                sysMenuRepository.deleteMenuBtn(sysMenu.getParentId());
                sysMenu.getChildren().stream().forEach(sysMenuRepository::insertMenu);
            }
        } else {
            sysMenuRepository.insertMenu(sysMenu);
            if (sysMenu.getChildren() != null && !sysMenu.getChildren().isEmpty()) {
                sysMenu.getChildren().stream().forEach(sysMenuRepository::insertMenu);
            }
        }
    }

    /**
     * 根据id查找菜单
     *
     * @param id
     * @return
     */
    @Override
    public SysMenu findMenuById(Long id) {
        return sysMenuRepository.findMenuById(id);
    }

    /**
     * 根据id删除菜单
     *
     * @param id
     */
    @Override
    public void deleteMenuById(Long id) {
        List<SysMenu> menuByParentId = sysMenuRepository.findMenuByParentId(id);
        if (menuByParentId.size() > 0) {
            sysMenuRepository.deleteMenu(id);
            sysMenuRepository.deleteMenuBtn(id);
        } else {
            sysMenuRepository.deleteMenu(id);
        }
    }

    /**
     * 根据角色id来查询菜单列表
     *
     * @param id
     * @return
     */
    @Override
    public List<SysMenu> findMenuByRoleId(Long id) {
        List<SysMenu> menuByRoleId = sysMenuRepository.findAllMenuAndBtn();
        List<SysMenu> roleMenu = sysMenuRepository.findMenuByRoleId(id);
        List<Long> collect = roleMenu.stream().map(SysMenu::getId).collect(Collectors.toList());
        menuByRoleId.stream().forEach(m -> {
            if (collect.contains(m.getId()) && m.getType() == 2) {
                m.setSelect(true);
            } else {
                m.setSelect(false);
            }
        });
        List<SysMenu> list = new ArrayList<>();
        menuByRoleId.stream().forEach(m -> {
            if (m.getParentId() == 0) {
                findNodes(m, menuByRoleId);
                list.add(m);
            }
        });

        return list;
    }

    /**
     * 给角色分配权限
     *
     * @param assignMenuVo
     */
    @Override
    public void doAssign(AssignMenuVo assignMenuVo) {
        sysRoleMenuRepository.deleteMenuByRoleId(assignMenuVo.getRoleId());
        if (assignMenuVo.getMenuIdLists() != null && !assignMenuVo.getMenuIdLists().isEmpty()) {
            sysRoleMenuRepository.insertRoleMenu(assignMenuVo.getRoleId(), assignMenuVo.getMenuIdLists());
        }
    }

    /**
     * 数据树形化
     */
    public SysMenu findNodes(SysMenu sysMenu, List<SysMenu> sysMenus) {
        sysMenus.stream().forEach(s -> {
            if (s.getParentId() == sysMenu.getId()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findNodes(s, sysMenus));
            }

        });
        return sysMenu;
    }
}
