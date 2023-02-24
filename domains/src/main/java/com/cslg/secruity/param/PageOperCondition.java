package com.cslg.secruity.param;

import com.cslg.condition.AbstractPagedCondition;
import lombok.Data;

@Data
public class PageOperCondition extends AbstractPagedCondition {
    /**
     * 日志开始时间
     */
    private String beginDate;
    /**
     * 日志结束时间
     */
    private String endDate;
    /**
     * 操作模块
     */
    private String moduleName;
    /**
     * 用户名称
     */
    private String username;
}
