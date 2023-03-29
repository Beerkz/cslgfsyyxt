package com.cslg.reserve;

import com.cslg.reserve.param.AuditParam;
import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveParam;
import com.cslg.reserve.vo.ReserveLabVo;
import com.cslg.vo.JsonPagedVO;
import com.cslg.vo.RestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "预约管理")
@RestController
@AllArgsConstructor
@RequestMapping("secured/reserve")
public class ReserveController {

    private final ReserveService reserveService;

    @PostMapping("page")
    @ApiOperation("分页查询")
    public RestBody<?> pageReserveLab() {
        return RestBody.ok();
    }

    /**
     * 开启预约
     */
    @PostMapping("start")
    @ApiOperation("开始预约")
    public RestBody<?> startReserve(@RequestBody StartReserveParam startReserveParam) {
        reserveService.startReserve(startReserveParam);
        return RestBody.ok();
    }

    /**
     * 分页查询预约
     */
    @PostMapping("do")
    @ApiOperation("待处理")
    public RestBody<?> doReserve(@RequestBody PageReserveCondition pageReserveCondition) {
        JsonPagedVO<ReserveLabVo> reserveLabVoJsonPagedVO = reserveService.pageAuditDoReserve(pageReserveCondition);
        return RestBody.okData(reserveLabVoJsonPagedVO);
    }

    /**
     * 预约处理
     */
    @PostMapping("audit")
    @ApiOperation("处理任务")
    public RestBody<?> auditReserve(@RequestBody AuditParam auditParam) {
        reserveService.auditReserve(auditParam);
        return RestBody.ok();
    }


    /**
     * 查询我的预约
     */
    @PostMapping("my/reserve")
    @ApiOperation("我的预约")
    public RestBody<?> myReserve(@RequestBody PageReserveCondition pageReserveCondition) {
        return RestBody.okData(reserveService.pageAuditMyReserve(pageReserveCondition));
    }

    @GetMapping("my/reserve/info/{id}")
    @ApiOperation("我的预约详细信息")
    public RestBody<?> myReserveInfo(@PathVariable("id") Long id) {
        return RestBody.okData(reserveService.getMyReserveInfo(id));
    }

}
