package com.cslg.deivce.repository;

import com.cslg.deivce.entity.NeedDeviceEntity;
import com.cslg.deivce.entity.NeedDeviceRelationship;
import com.cslg.deivce.param.PageNeedDeviceCondition;
import com.cslg.deivce.vo.NeedDeviceVo;
import com.cslg.system.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NeedDeviceRepository {

    Long countPageNeedDevice(PageNeedDeviceCondition pageNeedDeviceCondition);

    List<NeedDeviceEntity> pageNeedDevice(PageNeedDeviceCondition pageNeedDeviceCondition);

    Integer insertNeedDevice(NeedDeviceVo deviceVo);

    Integer updateNeedDevice(NeedDeviceVo deviceVo);
    Integer deleteNeedDevice(@Param("id") Long id);
    Integer insertNeedDeviceAndProviderRelationship(NeedDeviceRelationship relationship);

    NeedDeviceVo getNeedDeviceInfoVo(@Param("id") Long id);

    List<SysUser> getProviderInfo(@Param("id") Long needDeviceId);
    Integer updateNeedDeviceStatus(@Param("id") String id);

    Integer countNeedDeviceStatus(@Param("providerId") Long providerId,@Param("needId") Long needId);


}
