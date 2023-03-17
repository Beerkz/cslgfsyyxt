package com.cslg.reserve;

import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveParam;
import com.cslg.reserve.vo.ReserveLabVo;

import java.util.List;

public interface ReserveService {
    List<ReserveLabVo> pageReserveLab(PageReserveCondition pageReserveCondition);

    boolean startReserve(StartReserveParam startReserveParam);

    List<ReserveLabVo> auditReserve(PageReserveCondition pageReserveCondition);
}
