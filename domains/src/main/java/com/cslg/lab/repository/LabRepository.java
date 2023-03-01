package com.cslg.lab.repository;

import cn.hutool.db.Page;
import com.cslg.lab.param.PageLabCondition;
import com.cslg.lab.vo.PageLabVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public class LabRepository {
    List<PageLabVo> page(PageLabCondition pageLabCondition);
}
