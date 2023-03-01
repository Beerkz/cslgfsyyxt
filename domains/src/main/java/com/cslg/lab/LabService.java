package com.cslg.lab;

import com.cslg.lab.vo.PageLabVo;
import com.cslg.lab.param.PageLabCondition;
import com.cslg.vo.JsonPagedVO;

public interface LabService {
    JsonPagedVO<PageLabVo> pageLab(PageLabCondition pageLabCondition);
}
