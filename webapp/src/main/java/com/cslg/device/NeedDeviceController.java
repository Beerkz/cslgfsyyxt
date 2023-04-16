package com.cslg.device;

import com.cslg.deivce.NeedDeviceService;
import com.cslg.deivce.entity.NeedDeviceEntity;
import com.cslg.deivce.param.PageNeedDeviceCondition;
import com.cslg.deivce.vo.NeedDeviceVo;
import com.cslg.vo.JsonPagedVO;
import com.cslg.vo.RestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("secured/need/device")
public class NeedDeviceController {
    private final NeedDeviceService needDeviceService;

    @PostMapping("page")
    public RestBody<?> page(@RequestBody PageNeedDeviceCondition pageNeedDeviceCondition){
        JsonPagedVO<NeedDeviceEntity> page = needDeviceService.page(pageNeedDeviceCondition);
        return RestBody.okData(page);
    }

    @PostMapping("insert")
    public RestBody<?> insertNeedDevice(@RequestBody NeedDeviceVo needDeviceVo){
        needDeviceService.insertOrUpdate(needDeviceVo);
        return RestBody.ok();
    }
    @PostMapping("update")
    public RestBody<?> updateNeedDevice(@RequestBody NeedDeviceVo needDeviceVo){
        needDeviceService.insertOrUpdate(needDeviceVo);
        return RestBody.ok();
    }

    @GetMapping("insert/provider/{needId}")
    public RestBody<?> insertProvider(@PathVariable Long needId){
        Integer integer = needDeviceService.insertProviderInfo(needId);
        return RestBody.ok();
    }

    @GetMapping("info/{id}")
    public RestBody<?> getDeviceInfo(@PathVariable Long id){
        NeedDeviceVo deviceInfo = needDeviceService.getDeviceInfo(id);
        return RestBody.okData(deviceInfo);
    }

    @GetMapping("delete/{id}")
    public RestBody<?> deleteNeedDevice(@PathVariable Long id){
        needDeviceService.deleteNeedDevice(id);
        return RestBody.ok();
    }

}
