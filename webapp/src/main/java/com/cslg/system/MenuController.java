package com.cslg.system;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.cslg.annoation.LoginConfigAnnotation;
import com.cslg.enums.BusinessType;
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
@SaCheckLogin
public class MenuController {
    private final SysMenuService sysMenuService;

    @ApiOperation("菜单列表")
    @PostMapping("findAllMenu")
    @LoginConfigAnnotation(title = "菜单管理", businessType = BusinessType.LIST)
    public RestBody<?> findAllMenu() {
        List<SysMenu> allMenu = sysMenuService.findAllMenu();
        return RestBody.okData(allMenu);
    }

    @ApiOperation("菜单添加")
    @PostMapping("saveOrUpdate")
    @SaCheckPermission("bnt.sysUser.add")
    @LoginConfigAnnotation(title = "菜单管理", businessType = BusinessType.INSERT)
    public RestBody<?> saveOrUpdate(@RequestBody SysMenu sysMenu) {
        sysMenuService.saveOrUpdateMenu(sysMenu);
        return RestBody.ok();
    }

    @ApiOperation("根据id查询菜单")
    @GetMapping("findMenuById/{id}")
    @LoginConfigAnnotation(title = "菜单管理", businessType = BusinessType.VIEW)
    public RestBody<?> findMenuById(@PathVariable Long id) {
        SysMenu menu = sysMenuService.findMenuById(id);
        return RestBody.okData(menu);
    }

    @ApiOperation("删除菜单")
    @GetMapping("deleteMenu/{id}")
    @SaCheckPermission("bnt.sysUser.remove")
    @LoginConfigAnnotation(title = "菜单管理", businessType = BusinessType.DELETE)
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
