package com.cslg.deivce;

import com.cslg.deivce.entity.DeviceEntity;
import com.cslg.deivce.param.PageDeviceCondition;
import com.cslg.deivce.vo.DeviceVo;
import com.cslg.vo.JsonPagedVO;
import org.springframework.stereotype.Service;

public interface DeviceService {
    /**
     * 分页查询
     *
     * @param pageDeviceCondition 分页查询条件
     * @return 分页查询封装类
     */
    JsonPagedVO<DeviceVo> pageDeviceByCondition(PageDeviceCondition pageDeviceCondition);

    /**
     * 添加或者修改设备信息
     *
     * @param
     * @return
     */
    Integer insertOrUpdateDevice(DeviceVo deviceVo);
}
