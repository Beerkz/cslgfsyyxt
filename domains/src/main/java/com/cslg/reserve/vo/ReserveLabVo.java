package com.cslg.reserve.vo;

import com.cslg.lab.entity.LabEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 预约的详细信息
 */
@Getter
@Setter
@ToString
public class ReserveLabVo extends LabEntity {
    /**
     * 预约开始时间
     */
    private String beginTime;
    /**
     * 预约结束时间
     */
    private String endTime;
    /**
     * 时间段
     */
    private String spliceTime;
    /**
     * 申请人姓名
     */
    private String reserveName;
    /**
     * 申请人电话
     */
    private String reserveTel;
    /**
     * 申请人邮箱
     */
    private String email;
    /**
     * 申请日期
     */
    private String reserveDate;
    /**
     * 流程状态
     */
    private String statusName;
    /**
     * 流程状态编码
     */
    private String statusCode;
    /**
     * 流程的key
     */
    private String proKey;
    /**
     * 理由
     */
    private String reason;


}
