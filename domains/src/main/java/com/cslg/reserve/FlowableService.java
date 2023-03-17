package com.cslg.reserve;

public interface FlowableService {
    /**
     * 启动流程
     *
     * @param key 流程的key
     * @return
     */
    Boolean startWorkFlow(String key);

    /**
     * 根据key获取任务完成任务
     *
     * @param key
     * @return
     */
    boolean completeTask(String key);
}
