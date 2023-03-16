package com.cslg.lab.entity;

import com.cslg.system.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LabSpliceTimeEntity extends BaseEntity {
    private String beginTime;
    private String endTime;
    private String status;
}
