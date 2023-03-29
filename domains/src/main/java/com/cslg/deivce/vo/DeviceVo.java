package com.cslg.deivce.vo;

import com.cslg.deivce.entity.DeviceEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeviceVo extends DeviceEntity {
    private Long labId;
    private String labName;
}
