package com.cslg.secruity.param;

import com.cslg.condition.AbstractPagedCondition;
import lombok.Data;

@Data
public class PageOperCondition extends AbstractPagedCondition {
    /**
     * 日志开始时间
     */
    private String startTime;
    /**
     * 日志结束时间
     */
    private String endTime;
    /**
     * 操作模块
     */
    private String moduleName;
}
