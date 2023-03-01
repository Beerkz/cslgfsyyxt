package com.cslg.lab.param;

import com.cslg.system.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
public class PageLabCondition extends BaseEntity {

    private String labName;

    private String deviceName;

}
