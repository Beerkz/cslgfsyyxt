package com.cslg.reserve.repository;

import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveCondition;
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
     * @param startReserveCondition
     */
    void insertReserve(StartReserveCondition startReserveCondition);
}
