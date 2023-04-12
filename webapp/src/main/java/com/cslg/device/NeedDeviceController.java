package com.cslg.device;

import com.cslg.deivce.NeedDeviceService;
import com.cslg.deivce.param.PageNeedDeviceCondition;
import com.cslg.vo.RestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("secured/need/device")
public class NeedDeviceController {
    private final NeedDeviceService needDeviceService;

    @PostMapping("page")
    public RestBody<?> page(@RequestBody PageNeedDeviceCondition pageNeedDeviceCondition){

        return RestBody.ok();
    }
}
