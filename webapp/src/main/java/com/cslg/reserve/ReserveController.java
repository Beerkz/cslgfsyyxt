package com.cslg.reserve;

import com.cslg.reserve.param.StartReserveCondition;
import com.cslg.vo.RestBody;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public RestBody<?> startReserve(StartReserveCondition startReserveCondition) {
        reserveService
    }
}
