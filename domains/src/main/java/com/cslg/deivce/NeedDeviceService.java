package com.cslg.deivce;

import com.cslg.deivce.entity.NeedDeviceEntity;
import com.cslg.deivce.param.PageNeedDeviceCondition;
import com.cslg.deivce.vo.NeedDeviceVo;
import com.cslg.vo.JsonPagedVO;

public interface NeedDeviceService {

    JsonPagedVO<NeedDeviceEntity> page(PageNeedDeviceCondition pageNeedDeviceCondition);

    Integer insertOrUpdate(NeedDeviceVo needDeviceVo);
}
