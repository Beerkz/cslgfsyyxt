package com.cslg.lab.vo;

import com.cslg.deivce.entity.DeviceEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LabVo {
    private List<DeviceEntity> deviceEntities;
}
