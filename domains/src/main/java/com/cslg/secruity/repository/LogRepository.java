package com.cslg.secruity.repository;

import com.cslg.secruity.entity.SysLoginLog;
import com.cslg.secruity.entity.SysOperLog;
import com.cslg.secruity.param.PageLogCondition;
import com.cslg.secruity.param.PageOperCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogRepository {
    /**
     * 插入登录日志
     *
     * @param sysLoginLog
     */
    void insertLoginLog(SysLoginLog sysLoginLog);

    /**
     * 插入操作日志
     *
     * @param sysOperLog
     */
    void insertOperLog(SysOperLog sysOperLog);

    /**
     * 统计登录日志数量
     *
     * @param pageLogCondition
     * @return
     */
    Long countPageLoginLog(PageLogCondition pageLogCondition);

    /**
     * 登录日志分页查询
     *
     * @param pageLogCondition
     * @return
     */
    List<SysLoginLog> pageLoginLog(PageLogCondition pageLogCondition);

    /**
     * 统计操作日志数量
     *
     * @param pageOperCondition
     * @return
     */
    Long countPageOperLog(PageOperCondition pageOperCondition);

    /**
     * 分页查询操作日志
     *
     * @param pageOperCondition
     * @return
     */
    List<SysOperLog> pageOperLog(PageOperCondition pageOperCondition);

    /**
     * 根据id删除登录日志
     *
     * @param id 登录日志id
     * @return
     */
    Integer deleteLoginLog(@Param("id") String id);

    /**
     * 根据id删除操作日志
     *
     * @param id 操作日志id
     * @return
     */
    Integer deleteOperLog(@Param("id") String id);

}
