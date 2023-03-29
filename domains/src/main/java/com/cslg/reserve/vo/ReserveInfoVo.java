package com.cslg.reserve.vo;

import com.cslg.reserve.entity.ReserveEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReserveInfoVo extends ReserveEntity {
    /**
     * 教师审核理由
     */
    private String teacherAuditReason;
    /**
     * 管理员审批理由
     */
    private String managerAuditReason;
    /**
     * 审批名称
     */
    private String stepName;
    /**
     * 状态名称
     */
    private String statusName;
    /**
     * 预约时间段
     */
    private String spliceTime;
    /**
     * 实验室名称
     */
    private String labName;
    /**
     * 实验室头像地址
     */
    private String labUrl;
}
