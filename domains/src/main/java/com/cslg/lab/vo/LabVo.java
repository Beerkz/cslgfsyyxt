package com.cslg.lab.vo;

import com.cslg.deivce.entity.DeviceEntity;
import com.cslg.lab.entity.LabEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Spliterator;

@Getter
@Setter
@ToString
public class LabVo extends LabEntity {
    private List<DeviceEntity> deviceEntities;
    private List<Long> spliceTimeIds;
}
