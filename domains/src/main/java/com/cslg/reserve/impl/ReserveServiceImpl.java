package com.cslg.reserve.impl;

import com.cslg.reserve.ReserveService;
import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveCondition;
import com.cslg.reserve.vo.ReserveLabVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveServiceImpl implements ReserveService {

    @Override
    public List<ReserveLabVo> pageReserveLab(PageReserveCondition pageReserveCondition) {
        return null;
    }

    @Override
    public boolean startReserve(StartReserveCondition startReserveCondition) {

        return false;
    }
}
