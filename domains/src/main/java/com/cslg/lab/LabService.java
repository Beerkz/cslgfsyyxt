package com.cslg.lab;

import com.cslg.lab.vo.PageLabVo;
import com.cslg.lab.param.PageLabCondition;
import com.cslg.vo.JsonPagedVO;

public interface LabService {
    /**
     * 实验室分页查询
     * @param pageLabCondition 分页查询条件
     * @return 分页封装类
     */
    JsonPagedVO<PageLabVo> pageLab(PageLabCondition pageLabCondition);

    /**
     * 插入或修改实验室
     * @param pageLabVo
     */
    void insertOrUpdateLab(PageLabVo pageLabVo);
}
