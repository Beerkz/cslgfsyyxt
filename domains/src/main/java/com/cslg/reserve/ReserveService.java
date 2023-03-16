package com.cslg.reserve;

import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveCondition;
import com.cslg.reserve.vo.ReserveLabVo;

import java.util.List;

public interface ReserveService {
    List<ReserveLabVo> pageReserveLab(PageReserveCondition pageReserveCondition);

    boolean startReserve(StartReserveCondition startReserveCondition);
}
