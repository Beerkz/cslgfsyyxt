package com.cslg.secruity;

import com.cslg.secruity.entity.SysLoginLog;
import com.cslg.secruity.param.PageLogCondition;
import com.cslg.secruity.param.PageOperCondition;
import com.cslg.vo.JsonPagedVO;
import com.cslg.vo.RestBody;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public RestBody<?> pageLoginLog(PageLogCondition pageLogCondition) {
        JsonPagedVO<SysLoginLog> sysLoginLogJsonPagedVO = logService.pageLoginLog(pageLogCondition);
        return RestBody.okData(sysLoginLogJsonPagedVO);
    }

    @PostMapping("page/operlog")
    public RestBody<?> pageOperLog(PageOperCondition pageOperCondition) {

    }
}
