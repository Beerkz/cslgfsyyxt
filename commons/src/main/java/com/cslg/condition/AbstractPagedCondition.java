package com.cslg.condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class AbstractPagedCondition {
    /**
     * 开始条数
     */
    private Integer start;
    /**
     * 每页限制数
     */
    private Integer limit;
    /**
     * 页数
     */
    private Integer page;
}
