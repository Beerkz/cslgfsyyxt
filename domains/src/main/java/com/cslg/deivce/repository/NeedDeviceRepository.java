package com.cslg.deivce.repository;

import com.cslg.deivce.entity.NeedDeviceEntity;
import com.cslg.deivce.param.PageNeedDeviceCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NeedDeviceRepository {

    Long countPageNeedDevice(PageNeedDeviceCondition pageNeedDeviceCondition);

    List<NeedDeviceEntity> pageNeedDevice(PageNeedDeviceCondition pageNeedDeviceCondition);
}
