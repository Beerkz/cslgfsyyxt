package com.cslg.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cslg.system.entity.SysRole;
import com.cslg.vo.RestBody;
import com.cslg.system.param.PageRoleCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequiredArgsConstructor
@Slf4j
public class SystemRoleController {
    private final SysRoleService sysRoleService;

    /**
     * 查询所有接口
     */
    @ApiOperation("角色查询所有")
    @GetMapping("findAllRole")
    public RestBody<?> findAllRole() {
        return RestBody.okData(sysRoleService.list());
    }

    /**
     * 逻辑删除接口
     *
     * @param id 角色id
     * @return
     */
    @ApiOperation("角色逻辑删除接口")
    @GetMapping("role/delete/{id}")
    public RestBody<?> deleteRoleById(@PathVariable Long id) {
        boolean b = sysRoleService.removeById(id);
        if (b) {
            return RestBody.ok();
        } else {
            return RestBody.failure("删除失败", "failure");
        }
    }

    /**
     * 角色分页查询
     *
     * @param pageRoleCondition 角色分页查询条件
     *                          start:开始条数
     *                          limit:结束条数
     */
    @ApiOperation("角色分页查询")
    @PostMapping("role/page")
    public RestBody<?> pageRoleByCondition(@RequestBody PageRoleCondition pageRoleCondition) {
        //创建page对象
        //Page<SysRole> pageRole = new Page<>(pageRoleCondition.getStart(), pageRoleCondition.getLimit());
        //调用service方法
        List<SysRole> sysRoles = sysRoleService.pageRoleByCondition(pageRoleCondition);
        return RestBody.okData(sysRoles);
    }

    @ApiOperation("角色添加")
    @PostMapping("role/insert")
    public RestBody<?> insertOrUpdateRole(@RequestBody SysRole sysRole) {
        //存在id就是修改，不存在就是增加
        log.info("增加或者修改属性:{}", sysRole.toString());
        boolean identifier = false;
        if (!StringUtils.hasText(sysRole.getId())) {
            identifier = sysRoleService.save(sysRole);
        } else {
            //sysRoleService.up
        }
        return RestBody.ok();
    }
}
