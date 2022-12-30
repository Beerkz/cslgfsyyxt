package com.cslg.condition;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class AbstractPagedCondition {
    private Integer start;
    private Integer limit;
    private Integer page;
}
