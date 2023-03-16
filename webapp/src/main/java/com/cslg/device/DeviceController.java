package com.cslg.device;

import com.cslg.deivce.param.PageDeviceCondition;
import com.cslg.vo.RestBody;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("secured/device")
@RestController
public class DeviceController {

    @PostMapping("page")
    @ApiOperation("分页查询设备")
    public RestBody<?> page(@RequestBody PageDeviceCondition pageDeviceCondition) {
        return RestBody.ok();
    }
}
