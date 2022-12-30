package com.cslg.system;

import com.cslg.system.entity.SysRole;
import com.cslg.vo.JsonPagedVO;
import com.cslg.vo.RestBody;
import com.cslg.system.param.PageRoleCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("system/role")
public class RoleController {
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
     * 根据id查询角色信息
     */
    @ApiOperation("根据id查询角色")
    @PostMapping("select/{id}")
    public RestBody<?> selectRoleById(@PathVariable Long id) {
        return RestBody.okData(sysRoleService.getById(id));
    }

    /**
     * 逻辑删除接口
     *
     * @param id 角色id
     * @return
     */
    @ApiOperation("角色逻辑删除接口")
    @GetMapping("delete/{id}")
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
    @PostMapping("page")
    public RestBody<?> pageRoleByCondition(@RequestBody PageRoleCondition pageRoleCondition) {
        //创建page对象
        //Page<SysRole> pageRole = new Page<>(pageRoleCondition.getStart(), pageRoleCondition.getLimit());
        //调用service方法
        JsonPagedVO<SysRole> sysRoleJsonPagedVO = sysRoleService.pageRoleByCondition(pageRoleCondition);
        return RestBody.okData(sysRoleJsonPagedVO);
    }

    /**
     * 角色添加修改接口
     *
     * @param sysRole
     * @return
     */
    @ApiOperation("角色添加/修改")
    @PostMapping("insertOrUpdate")
    public RestBody<?> insertOrUpdateRole(@RequestBody SysRole sysRole) {
        //存在id就是修改，不存在就是增加
        log.info("增加或者修改属性:{}", sysRole.toString());
        Boolean success = sysRoleService.insertOrUpdateRole(sysRole);
        if (success) {
            return RestBody.ok();
        } else {
            return RestBody.failure("201", "增加或修改失败");
        }
    }

    /**
     * 批量删除角色
     *
     * @param ids id集合 json数组格式 --- java的list集合
     * @return
     */
    @ApiOperation("角色批量删除")
    @DeleteMapping("batchremove")
    public RestBody<?> removeRoleByIds(@RequestBody List<Long> ids) {
        log.info("批量删除角色Id:{}", ids);
        sysRoleService.removeByIds(ids);
        return RestBody.ok();
    }
}
