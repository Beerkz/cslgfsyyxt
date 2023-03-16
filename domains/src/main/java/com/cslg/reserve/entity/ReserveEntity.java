package com.cslg.reserve.entity;

import com.cslg.system.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 预约实体类
 */
@Getter
@Setter
@ToString
public class ReserveEntity extends BaseEntity {
    /**
     * 实验室id
     */
    private Long labId;
    /**
     * 预约用户id
     */
    private Long userId;
    /**
     * 预约时间
     */
    private String reserveDate;
    /**
     * 时间段id
     */
    private Long spliceTimeId;
    /**
     * 流程的key查询当前流程的
     */
    private String pro_key;
    /**
     * 申请理由
     */
    private String reason;
}
