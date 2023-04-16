package com.cslg.deivce;

import com.cslg.deivce.entity.NeedDeviceEntity;
import com.cslg.deivce.param.PageNeedDeviceCondition;
import com.cslg.deivce.vo.NeedDeviceVo;
import com.cslg.system.entity.SysUser;
import com.cslg.vo.JsonPagedVO;

import java.util.List;

public interface NeedDeviceService {

    JsonPagedVO<NeedDeviceEntity> page(PageNeedDeviceCondition pageNeedDeviceCondition);

    Integer insertOrUpdate(NeedDeviceVo needDeviceVo);

    NeedDeviceVo getDeviceInfo(Long id);

    Integer insertProviderInfo(Long needId);

    Integer deleteNeedDevice(Long id);

}
