package com.cslg.reserve;

import com.cslg.reserve.param.AuditParam;
import com.cslg.reserve.param.StartReserveParam;

public interface FlowableService {
    /**
     * 启动流程
     *
     * @param key 流程的key
     * @return
     */
    Boolean startWorkFlow(StartReserveParam key);

    /**
     * 根据key获取任务完成任务
     *
     * @param auditParam reason：审核理由
     * @return
     */
    boolean completeTask(AuditParam auditParam);
}
