package com.cslg.secruity.param;

import com.cslg.condition.AbstractPagedCondition;
import lombok.Data;

@Data
public class PageLogCondition extends AbstractPagedCondition {
    /**
     * 日志开始时间
     */
    private String beginDate;
    /**
     * 日志结束时间
     */
    private String endDate;
    /**
     * 用户名
     */
    private String username;
}
