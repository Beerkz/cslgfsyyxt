package com.cslg.lab.repository;

import com.cslg.lab.entity.LabEntity;
import com.cslg.lab.entity.LabSpliceTimeEntity;
import com.cslg.lab.param.PageLabCondition;
import com.cslg.lab.vo.LabSpliceTimeVo;
import com.cslg.lab.vo.LabVo;
import com.cslg.lab.vo.PageLabVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LabRepository {
    /**
     * 分页查询计数
     * @param pageLabCondition
     * @return
     */
    Long countPage(PageLabCondition pageLabCondition);
    /**
     * 分页查询实验室
     * @param pageLabCondition
     * @return
     */
    List<PageLabVo> page(PageLabCondition pageLabCondition);

    /**
     * 插入实验室信息
     * @param LabEntity
     * @return
     */
    Integer insertLab(LabEntity LabEntity);

    /**
     * 修改实验室信息
     * @param labEntity
     * @return
     */
    Integer updateLab(LabEntity labEntity);

    /**
     * 查看实验室信息
     *
     * @param id
     * @return
     */
    LabEntity viewLab(@Param("id") Long id);

    /**
     * 获取所有时间段信息
     *
     * @return
     */
    List<LabSpliceTimeEntity> getAllSpliceTime();

    /**
     * 删除实验室开放的时间段
     *
     * @param id 实验室id
     * @return
     */
    Integer deleteSpliceTimeById(@Param("id") Long id);

    /**
     * 批量插入所有时间段
     *
     * @param labSpliceTimeVoList
     * @return
     */
    Integer insertSpliceTime(@Param("items") List<Long> labSpliceTimeVoList, @Param("id") Long id);

    /**
     * 根据id获取实验室信息
     *
     * @param id
     * @return 实验室信息
     */
    LabVo getLabInfo(@Param("id") Long id);

    /**
     * 根据实验室id获取实验室开放分时的时间段
     *
     * @param id 实验室id
     * @return
     */
    List<Long> getLabSpliceTimeIds(@Param("id") Long id);

    /**
     * 根据id删除实验室信息
     *
     * @param id
     */
    void deleteLabById(@Param("id") Long id);
}
