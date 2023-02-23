package com.cslg.secruity.impl;

import com.cslg.secruity.LogService;
import com.cslg.secruity.entity.SysLoginLog;
import com.cslg.secruity.entity.SysOperLog;
import com.cslg.secruity.param.PageLogCondition;
import com.cslg.secruity.param.PageOperCondition;
import com.cslg.secruity.repository.LogRepository;
import com.cslg.system.entity.SysUser;
import com.cslg.vo.JsonPagedVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 日志实现类
 */
@Service
@AllArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;

    /**
     * 登录日志插入
     *
     * @param sysLoginLog 日志信息
     */
    @Override
    public void insertLoginLog(SysLoginLog sysLoginLog) {

        logRepository.insertLoginLog(sysLoginLog);
    }

    /**
     * 分页查询日志
     *
     * @param pageLogCondition
     * @return
     */
    @Override
    public JsonPagedVO<SysLoginLog> pageLoginLog(PageLogCondition pageLogCondition) {
        final List<SysLoginLog> loginLogs;
        Long counts = logRepository.countPageLoginLog(pageLogCondition);
        if (counts > 0) {
            loginLogs = logRepository.pageLoginLog(pageLogCondition);
        } else {
            loginLogs = Collections.emptyList();
        }
        return new JsonPagedVO<>(loginLogs, counts);
    }

    @Override
    public JsonPagedVO<SysOperLog> pageOperLog(PageOperCondition pageOperCondition) {
        final List<SysOperLog> sysOperLogs;
        Long count = logRepository.countPageOperLog(pageOperCondition);
        if (count > 0) {
            sysOperLogs = logRepository.pageOperLog(pageOperCondition);
        } else {
            sysOperLogs = Collections.emptyList();
        }
        return new JsonPagedVO<>(sysOperLogs, count);
    }

    /**
     * 删除登录日志
     *
     * @param id
     */
    @Override
    public void deleteLoginLog(String id) {
        logRepository.deleteLoginLog(id);
    }

    /**
     * 删除操作日志
     *
     * @param id
     */
    @Override
    public void deleteOperLog(String id) {
        logRepository.deleteOperLog(id);
    }
}
