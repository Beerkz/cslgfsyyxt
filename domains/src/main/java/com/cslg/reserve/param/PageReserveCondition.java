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
    private String labName;

    /**
     * 预约时间
     */
    private String reserveDate;

    /**
     * 预约时间段
     */
    private Long spliceTimeId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 状态
     */
    private String status;

    /**
     * 电话
     */

    private String phone;

    /**
     * 预约人姓名
     */
    private String username;


}
