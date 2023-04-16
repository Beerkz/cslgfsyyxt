package com.cslg.deivce.entity;

import com.cslg.system.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NeedDeviceRelationship extends BaseEntity {
private String status;

private Long tNeedDeviceId;
private Long tProviderId;
private String tOrderId;
}
