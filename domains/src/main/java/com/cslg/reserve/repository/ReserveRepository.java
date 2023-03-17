package com.cslg.reserve.repository;

import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveParam;
import com.cslg.reserve.vo.ReserveLabVo;
import org.apache.ibatis.annotations.Mapper;
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
     * 查看是否有一样的预约情况
     */
    Integer countReserveByParam(StartReserveParam startReserveParam);

    /**
     * 获取用户预约信息
     */
    ReserveLabVo getReserveInfo(PageReserveCondition pageReserveCondition);
}
