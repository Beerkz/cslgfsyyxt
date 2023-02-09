package com.cslg.system;


import com.cslg.system.entity.SysMenu;
import com.cslg.system.vo.AssignMenuVo;
import com.cslg.vo.RestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("system/menu")
@AllArgsConstructor
public class MenuController {
    private final SysMenuService sysMenuService;

    @ApiOperation("菜单列表")
    @PostMapping("findAllMenu")
    public RestBody<?> findAllMenu() {
        List<SysMenu> allMenu = sysMenuService.findAllMenu();
        return RestBody.okData(allMenu);
    }

    @ApiOperation("菜单添加")
    @PostMapping("saveOrUpdate")
    public RestBody<?> saveOrUpdate(@RequestBody SysMenu sysMenu) {
        sysMenuService.saveOrUpdateMenu(sysMenu);
        return RestBody.ok();
    }

    @ApiOperation("根据id查询菜单")
    @GetMapping("findMenuById/{id}")
    public RestBody<?> findMenuById(@PathVariable Long id) {
        SysMenu menu = sysMenuService.findMenuById(id);
        return RestBody.okData(menu);
    }

    @ApiOperation("删除菜单")
    @GetMapping("deleteMenu/{id}")
    public RestBody<?> deleteMenuById(@PathVariable Long id) {
        sysMenuService.deleteMenuById(id);
        return RestBody.ok();
    }

    @ApiOperation("根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public RestBody<?> toAssign(@PathVariable Long roleId) {
        return RestBody.okData(sysMenuService.findMenuByRoleId(roleId));
    }

    @ApiOperation("根据角色来分配菜单")
    @PostMapping("/doAssign")
    public RestBody<?> doAssign(@RequestBody AssignMenuVo assignMenuVo) {
        sysMenuService.doAssign(assignMenuVo);
        return RestBody.ok();
    }
}
