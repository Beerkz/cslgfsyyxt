package com.cslg.device;

import com.cslg.deivce.DeviceService;
import com.cslg.deivce.param.PageDeviceCondition;
import com.cslg.deivce.vo.DeviceVo;
import com.cslg.vo.RestBody;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("info/{id}")
    @ApiOperation("查看设备详细信息")
    public RestBody<?> getDeviceInfo(@PathVariable Long id){
        DeviceVo deviceInfo = deviceService.getDeviceInfo(id);
        return RestBody.okData(deviceInfo);
    }

    @GetMapping("delete/{id}")
    @ApiOperation("删除设备")
    public RestBody<?> deleteDevice(@PathVariable Long id){
        deviceService.deleteDevice(id);
        return RestBody.ok();
    }
}
