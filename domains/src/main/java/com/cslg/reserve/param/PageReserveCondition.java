package com.cslg.reserve.param;

import com.cslg.condition.AbstractPagedCondition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageReserveCondition extends AbstractPagedCondition {
    /**
     * 实验室名称
     */
    public String labName;
}
