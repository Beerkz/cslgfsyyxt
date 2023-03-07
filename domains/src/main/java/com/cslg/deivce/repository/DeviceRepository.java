package com.cslg.deivce.repository;

import com.cslg.deivce.entity.DeviceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceRepository {
    List<DeviceEntity> findDeviceByLabId(@Param("id") Long id);
    Integer deleteLabAndDeviceRelationship(@Param("id")Long id);
    Integer insertLabAndDeviceRelationship(@Param("labId")Long labId,@Param("deviceId")Long deviceId);
}
