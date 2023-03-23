package com.cslg.reserve.repository;

import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveParam;
import com.cslg.reserve.vo.ReserveLabVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReserveRepository {
    List<ReserveLabVo> pageReserveLab(PageReserveCondition pageReserveCondition);

    /**
     * 插入预约记录
     *
     * @param startReserveParam
     */
    void insertReserve(StartReserveParam startReserveParam);

    /**
     * 更改预约状态
     */
    void updateReserve(StartReserveParam startReserveParam);

    /**
     * 查看是否有一样的预约情况,同一天，同一个时间段，同一个人
     */
    Integer countReserveByParam(StartReserveParam startReserveParam);

    /**
     * 获取用户预约信息
     */
    ReserveLabVo getReserveInfo(PageReserveCondition pageReserveCondition);

    /**
     * 根据流程key获取预约信息
     */
    ReserveLabVo getReserveInfoByKey(@Param("key") String key);

    /**
     * 根据流程的key分页查询信息
     */
    Long countReserveInfo(@Param("params") PageReserveCondition pageReserveCondition, @Param("items") List<String> keys);

    /**
     * 根据流程key分页查询信息
     */
    List<ReserveLabVo> pageReserveInfo(@Param("params") PageReserveCondition pageReserveCondition, @Param("items") List<String> keys);

    /**
     * 根据id查询预约的详细信息
     */
    ReserveLabVo getReserveInfoById(@Param("id") Long id);

    /**
     * 更新预约状态位
     * 0审核 1通过 2不通过
     */
    void updateReserveStatus(@Param("id") Long id, @Param("status") Long status);

    /**
     * 根据用户id统计所有预约流程的proKey
     */
    Long countMyReserveInfo(PageReserveCondition pageReserveCondition);

    /**
     * 根据用户id查询所有预约流程的proKey
     */
    List<ReserveLabVo> pageMyReserveInfo(PageReserveCondition pageReserveCondition);

}
