package com.cslg.deivce.impl;

import com.cslg.deivce.DeviceService;
import com.cslg.deivce.entity.DeviceEntity;
import com.cslg.deivce.param.PageDeviceCondition;
import com.cslg.deivce.repository.DeviceRepository;
import com.cslg.deivce.vo.DeviceVo;
import com.cslg.vo.JsonPagedVO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    @Override
    public JsonPagedVO<DeviceVo> pageDeviceByCondition(PageDeviceCondition pageDeviceCondition) {
        List<DeviceVo> deviceVos;
        Long countPageDevice = deviceRepository.countPageDevice(pageDeviceCondition);
        if (countPageDevice > 0) {
            deviceVos = deviceRepository.pageDevice(pageDeviceCondition);
        } else {
            deviceVos = Collections.emptyList();
        }
        return new JsonPagedVO<>(deviceVos, countPageDevice);
    }

    /**
     * 增加或修改设备信息
     *
     * @param deviceVo
     * @return
     */
    @Override
    public Integer insertOrUpdateDevice(DeviceVo deviceVo) {
        if (deviceVo.getId() != null) {
            deviceRepository.deleteLabAndDeviceRelationship(deviceVo.getId());
            deviceRepository.insertDevice(deviceVo);
            if (deviceVo.getLabId() != null && !StringUtils.isEmpty(deviceVo.getLabName())) {
                deviceRepository.insertLabAndDeviceRelationship(deviceVo.getLabId(), deviceVo.getId());
            }
        } else {
            deviceRepository.insertDevice(deviceVo);
            if (deviceVo.getLabId() != null && !StringUtils.isEmpty(deviceVo.getLabName())) {
                deviceRepository.insertLabAndDeviceRelationship(deviceVo.getLabId(), deviceVo.getId());

            }
        }
        return null;
    }
}
