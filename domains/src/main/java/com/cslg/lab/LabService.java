package com.cslg.lab;

import com.cslg.lab.entity.LabEntity;
import com.cslg.lab.entity.LabSpliceTimeEntity;
import com.cslg.lab.vo.LabSpliceTimeVo;
import com.cslg.lab.vo.LabVo;
import com.cslg.lab.vo.PageLabVo;
import com.cslg.lab.param.PageLabCondition;
import com.cslg.vo.JsonPagedVO;

import java.util.List;

public interface LabService {
    /**
     * 实验室分页查询
     *
     * @param pageLabCondition 分页查询条件
     * @return 分页封装类
     */
    JsonPagedVO<PageLabVo> pageLab(PageLabCondition pageLabCondition);

    /**
     * 插入或修改实验室
     *
     * @param pageLabVo
     */
    void insertOrUpdateLab(PageLabVo pageLabVo);

    /**
     * 获取所有分片时间段
     *
     * @return
     */
    List<LabSpliceTimeVo> selectSpliceTime();

    /**
     * 根据实验室id获取实验室信息
     *
     * @param id
     * @return
     */
    LabVo getLabInfo(Long id);

    void deleteInfo(Long id);

    List<LabVo> getAllLab();

}
