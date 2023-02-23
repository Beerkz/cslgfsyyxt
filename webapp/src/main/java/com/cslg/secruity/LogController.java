package com.cslg.secruity;

import com.cslg.secruity.entity.SysLoginLog;
import com.cslg.secruity.entity.SysOperLog;
import com.cslg.secruity.param.PageLogCondition;
import com.cslg.secruity.param.PageOperCondition;
import com.cslg.vo.JsonPagedVO;
import com.cslg.vo.RestBody;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiOperation("日志接口")
@RequestMapping("system/log")
@RequiredArgsConstructor
public class LogController {
    private final LogService logService;

    /**
     * 分页查询日志信息
     *
     * @param pageLogCondition 查询条件
     * @return
     */
    @PostMapping("page/loginlog")
    @ApiOperation("分页查询登录日志")
    public RestBody<?> pageLoginLog(@RequestBody PageLogCondition pageLogCondition) {
        JsonPagedVO<SysLoginLog> sysLoginLogJsonPagedVO = logService.pageLoginLog(pageLogCondition);
        return RestBody.okData(sysLoginLogJsonPagedVO);
    }

    /**
     * 删除登录日志
     *
     * @param id 登录日志id
     * @return
     */
    @GetMapping("page/deleteLoginLog/{id}")
    @ApiOperation("删除登录日志")
    public RestBody<?> deleteLoginLog(@PathVariable String id) {
        logService.deleteLoginLog(id);
        return RestBody.ok();
    }

    /**
     * 删除操作日志
     *
     * @param id 操作日志id
     * @return
     */
    @GetMapping("page/deleteOperLog/{id}")
    @ApiOperation("删除操作日志")
    public RestBody<?> deleteOperLog(@PathVariable String id) {
        logService.deleteOperLog(id);
        return RestBody.ok();
    }

    /**
     * 分页查询模块日志
     *
     * @param pageOperCondition
     * @return
     */
    @PostMapping("page/operlog")
    public RestBody<?> pageOperLog(@RequestBody PageOperCondition pageOperCondition) {
        JsonPagedVO<SysOperLog> sysOperLogJsonPagedVO = logService.pageOperLog(pageOperCondition);
        return RestBody.okData(sysOperLogJsonPagedVO);
    }
}
