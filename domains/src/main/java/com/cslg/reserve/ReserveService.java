package com.cslg.reserve;

import com.cslg.reserve.param.AuditParam;
import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveParam;
import com.cslg.reserve.vo.ReserveInfoVo;
import com.cslg.reserve.vo.ReserveLabVo;
import com.cslg.vo.JsonPagedVO;

import java.util.List;

public interface ReserveService {
    List<ReserveLabVo> pageReserveLab(PageReserveCondition pageReserveCondition);

    boolean startReserve(StartReserveParam startReserveParam);

    JsonPagedVO<ReserveLabVo> pageAuditDoReserve(PageReserveCondition pageReserveCondition);

    /**
     * 流程审批
     *
     * @param auditParam 预约信息
     */
    void auditReserve(AuditParam auditParam);


    /**
     * 分页查询我的预约
     *
     * @param pageReserveCondition
     * @return
     */
    JsonPagedVO<ReserveLabVo> pageAuditMyReserve(PageReserveCondition pageReserveCondition);

    /**
     * 获取我的预约审核详细信息
     *
     * @param id 预约id
     * @return
     */
    ReserveInfoVo getMyReserveInfo(Long id);
}
