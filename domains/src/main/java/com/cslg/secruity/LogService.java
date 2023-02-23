package com.cslg.secruity;

import com.cslg.secruity.entity.SysLoginLog;
import com.cslg.secruity.entity.SysOperLog;
import com.cslg.secruity.param.PageLogCondition;
import com.cslg.secruity.param.PageOperCondition;
import com.cslg.system.entity.SysUser;
import com.cslg.vo.JsonPagedVO;

import java.util.List;

public interface LogService {
    /**
     * 插入日志
     *
     * @param sysLoginLog
     */
    void insertLoginLog(SysLoginLog sysLoginLog);

    /**
     * 分页查询登录日志
     */
    JsonPagedVO<SysLoginLog> pageLoginLog(PageLogCondition pageLogCondition);

    /**
     * 分页查询访问日志
     *
     * @param pageOperCondition
     * @return
     */
    JsonPagedVO<SysOperLog> pageOperLog(PageOperCondition pageOperCondition);


    void deleteLoginLog(String id);

    void deleteOperLog(String id);
}
