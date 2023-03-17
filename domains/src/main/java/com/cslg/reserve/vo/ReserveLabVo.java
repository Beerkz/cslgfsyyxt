package com.cslg.reserve.vo;

import com.cslg.lab.entity.LabEntity;

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

}
