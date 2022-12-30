package com.cslg.system.impl;

import com.cslg.system.SysMenuService;
import com.cslg.system.entity.SysMenu;
import com.cslg.system.repository.SysMenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {

    private final SysMenuRepository sysMenuRepository;

    @Override
    public List<SysMenu> findAllMenu() {
        return sysMenuRepository.findAllMenu();
    }

    @Override
    public void saveOrUpdateMenu(SysMenu sysMenu) {
        if (sysMenu.getId() != null) {
            sysMenuRepository.updateMenu(sysMenu);
            if (!sysMenu.getChildren().isEmpty()) {
                sysMenuRepository.deleteMenuBtn(sysMenu.getParentId());
                sysMenu.getChildren().stream().forEach(sysMenuRepository::insertMenu);
            }
        } else {
            sysMenuRepository.insertMenu(sysMenu);
        }
    }
}
