package com.cslg.lab.vo;

import com.cslg.deivce.entity.DeviceEntity;
import com.cslg.lab.entity.LabEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PageLabVo extends LabEntity {
    List<DeviceEntity> deviceEntities;

    List<Long> spliceTimeIds;

    private String labManagerName;

    private String managerPhone;
}
