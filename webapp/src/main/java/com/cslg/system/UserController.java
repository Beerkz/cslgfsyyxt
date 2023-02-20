package com.cslg.system;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.cslg.system.entity.SysUser;
import com.cslg.system.param.PageUserCondition;
import com.cslg.vo.JsonPagedVO;
import com.cslg.vo.RestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理接口")
@Slf4j
@RestController
@RequestMapping("system/user")
@AllArgsConstructor
@SaCheckLogin
public class UserController {

    private SysUserService sysUserService;

    @ApiOperation("用户分页查询")
    @PostMapping("page")
    public RestBody<?> pagedUserByCondition(@RequestBody PageUserCondition pageUserCondition) {
        JsonPagedVO<SysUser> listJsonPagedVO = sysUserService.pagedUserByCondition(pageUserCondition);
        return RestBody.okData(listJsonPagedVO);
    }

    @ApiOperation("用户添加")
    @PostMapping("add")
    @SaCheckPermission("bnt.sysUser.add")
    public RestBody<?> addUser(@RequestBody SysUser sysUser) {
        sysUserService.insertOrUpdateUser(sysUser);
        return RestBody.ok();
    }

    @ApiOperation("用户修改")
    @PostMapping("update")
    @SaCheckPermission("bnt.sysUser.update")
    public RestBody<?> updateUser(@RequestBody SysUser sysUser) {
        sysUserService.insertOrUpdateUser(sysUser);
        return RestBody.ok();
    }

    @ApiOperation("根据id查看用户信息")
    @GetMapping("view/{id}")
    @SaCheckPermission("bnt.sysUser.list")
    public RestBody<?> viewUserById(@PathVariable Long id) {
        SysUser user = sysUserService.getUserById(id);
        return RestBody.okData(user);
    }

    @ApiOperation("启用禁用用户")
    @PostMapping("enable")
    public RestBody<?> enableUser(@RequestBody SysUser sysUser) {
        sysUserService.enableUser(sysUser);
        return RestBody.ok();
    }

    @ApiOperation("删除用户")
    @GetMapping("delete/{id}")
    @SaCheckPermission("bnt.sysUser.remove")
    public RestBody<?> deleteUser(@PathVariable Long id) {
        sysUserService.deleteUser(id);
        return RestBody.ok();
    }
}
