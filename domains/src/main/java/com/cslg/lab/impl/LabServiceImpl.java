package com.cslg.lab.impl;

import com.cslg.lab.LabService;
import com.cslg.lab.param.PageLabCondition;
import com.cslg.lab.vo.PageLabVo;
import com.cslg.vo.JsonPagedVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LabServiceImpl implements LabService {

    @Override
    public JsonPagedVO<PageLabVo> pageLab(PageLabCondition pageLabCondition) {
        return null;
    }
}
