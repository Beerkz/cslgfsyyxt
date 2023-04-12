package com.cslg.deivce.impl;

import com.cslg.deivce.NeedDeviceService;
import com.cslg.deivce.entity.NeedDeviceEntity;
import com.cslg.deivce.param.PageNeedDeviceCondition;
import com.cslg.deivce.repository.NeedDeviceRepository;
import com.cslg.deivce.vo.DeviceVo;
import com.cslg.vo.JsonPagedVO;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class NeedDeviceImpl implements NeedDeviceService {
    private final NeedDeviceRepository needDeviceRepository;
    @Override
    public JsonPagedVO<NeedDeviceEntity> page(PageNeedDeviceCondition pageNeedDeviceCondition) {
        List<NeedDeviceEntity> deviceVos;
        Long countPageDevice = needDeviceRepository.countPageNeedDevice(pageNeedDeviceCondition);
        if (countPageDevice > 0) {
            deviceVos = needDeviceRepository.pageNeedDevice(pageNeedDeviceCondition);
        } else {
            deviceVos = Collections.emptyList();
        }
        return new JsonPagedVO<>(deviceVos, countPageDevice);
    }
}
