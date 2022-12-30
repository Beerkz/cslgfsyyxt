package com.cslg.system;


import com.cslg.system.entity.SysMenu;
import com.cslg.vo.RestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "菜单管理")
@RestController
@RequestMapping("system/menu")
@AllArgsConstructor
public class MenuController {
    private final SysMenuService sysMenuService;

    @ApiOperation("菜单列表")
    @GetMapping("findAllMenu")
    public RestBody<?> findAllMenu() {
        List<SysMenu> allMenu = sysMenuService.findAllMenu();
        return RestBody.okData(allMenu);
    }

    @ApiOperation("菜单添加")
    @PostMapping("save")
    public RestBody<?> saveMenu(SysMenu sysMenu) {
        sysMenuService.saveOrUpdateMenu(sysMenu);
        return RestBody.ok();
    }
}
