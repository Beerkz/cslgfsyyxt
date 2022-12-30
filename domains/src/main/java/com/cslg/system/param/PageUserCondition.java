package com.cslg.system.param;

import com.cslg.condition.AbstractPagedCondition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

/**
 * 用户分页查询条件类
 */
@Getter
@Setter
@ToString
public class PageUserCondition extends AbstractPagedCondition {
    /**
     * 用户名
     */
    private String username;
    /**
     * 学号
     */
    private String stuNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 部门
     */
    private Long deptId;
    /**
     * 职位
     */
    private Long positionId;
    /**
     * 角色
     */
    private Long roleId;
    /**
     * 开始时间
     */
    private String beginDate;
    /**
     * 结束时间
     */
    private String endDate;
    /**
     * 电话
     */
    private String phone;
}
