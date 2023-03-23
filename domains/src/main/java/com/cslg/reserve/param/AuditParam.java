package com.cslg.reserve.param;

import io.github.classgraph.utils.LogNode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 流程处理条件
 */
@Getter
@Setter
@ToString
public class AuditParam {
    /**
     * 预约id
     */
    private Long id;
    /**
     * 审批理由
     */
    private String reason;
    /**
     * 流程的key
     */
    private String proKey;
    /**
     * 1不通过 0通过
     */
    private Long result;
}
