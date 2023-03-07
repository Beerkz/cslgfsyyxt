package com.cslg.lab.repository;

import cn.hutool.db.Page;
import com.cslg.lab.entity.LabEntity;
import com.cslg.lab.param.PageLabCondition;
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
     * @param id
     * @return
     */
    LabEntity viewLab(@Param("id") Long id);
}
