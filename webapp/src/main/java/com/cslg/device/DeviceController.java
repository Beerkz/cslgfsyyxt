package com.cslg.device;

import com.cslg.deivce.DeviceService;
import com.cslg.deivce.param.PageDeviceCondition;
import com.cslg.deivce.vo.DeviceVo;
import com.cslg.vo.RestBody;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("secured/device")
@RestController
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("page")
    @ApiOperation("分页查询设备")
    public RestBody<?> page(@RequestBody PageDeviceCondition pageDeviceCondition) {
        return RestBody.okData(deviceService.pageDeviceByCondition(pageDeviceCondition));
    }


    @PostMapping("insert")
    @ApiOperation("增加")
    public RestBody<?> insert(@RequestBody DeviceVo deviceVo) {
        deviceService.insertOrUpdateDevice(deviceVo);
        return RestBody.ok();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public RestBody<?> update(@RequestBody DeviceVo deviceVo) {
        deviceService.insertOrUpdateDevice(deviceVo);
        return RestBody.ok();
    }
}
