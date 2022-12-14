package com.cslg.system.param;

import com.cslg.condition.AbstractPagedCondition;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageRoleCondition extends AbstractPagedCondition {
    private String roleName;

}
