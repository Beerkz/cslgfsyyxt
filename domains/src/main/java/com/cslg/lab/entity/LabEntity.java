package com.cslg.lab.entity;

import com.cslg.system.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LabEntity extends BaseEntity {
    private String labName;
    private String latitude;
    private String longitude;
    private String Introduction;
    private String warning;
    private String capacity;
    private String labManagerId;
    private String labUrl;
    private Integer status;
}
