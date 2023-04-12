package com.cslg.deivce.entity;

import com.cslg.system.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NeedDeviceEntity extends BaseEntity {
    private String deviceName;
    private String deviceUsage;
    private String deviceParam;
    private String devicePrinciple;
    private String deviceModel;
    private String project;
    private String status;
    private String deviceUrl;
    private Integer providerId;
}
