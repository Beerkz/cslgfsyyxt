package com.cslg.deivce.param;

import com.cslg.condition.AbstractPagedCondition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageNeedDeviceCondition extends AbstractPagedCondition {
    private String deviceName;

}
