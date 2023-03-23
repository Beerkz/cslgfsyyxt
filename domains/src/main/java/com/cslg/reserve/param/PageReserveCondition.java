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

    /**
     * 预约时间
     */
    public String reserveDate;

    /**
     * 预约时间段
     */
    public Long spliceTimeId;

    /**
     * 用户id
     */
    public Long userId;

    /**
     * 状态
     */
    public String status;

}
