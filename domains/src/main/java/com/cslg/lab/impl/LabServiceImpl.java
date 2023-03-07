package com.cslg.lab.impl;

import com.cslg.deivce.entity.DeviceEntity;
import com.cslg.deivce.repository.DeviceRepository;
import com.cslg.lab.LabService;
import com.cslg.lab.param.PageLabCondition;
import com.cslg.lab.repository.LabRepository;
import com.cslg.lab.vo.PageLabVo;
import com.cslg.vo.JsonPagedVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LabServiceImpl implements LabService {
    private final LabRepository labRepository;
    private final DeviceRepository deviceRepository;
    @Override
    public JsonPagedVO<PageLabVo> pageLab(PageLabCondition pageLabCondition) {
        final List<PageLabVo> pageLabVos;
        Long count = labRepository.countPage(pageLabCondition);
        if (count>0){
            pageLabVos = labRepository.page(pageLabCondition);

        }else {
            pageLabVos = Collections.emptyList();
        }

        return new JsonPagedVO<>(pageLabVos,count);
    }

    /**
     * 更新或者插入实验室信息
     * @param pageLabVo
     * 实验室信息和设施信息
     */
    @Override
    public void insertOrUpdateLab(PageLabVo pageLabVo) {
        final List<Long> deviceIdList;
        if (pageLabVo.getDeviceEntities()!=null && !pageLabVo.getDeviceEntities().isEmpty()){
            deviceIdList = pageLabVo.getDeviceEntities().stream().map(DeviceEntity::getId).collect(Collectors.toList());
        }else {
            deviceIdList = Collections.emptyList();
        }
        if (pageLabVo.getId()!=null){
            labRepository.updateLab(pageLabVo);
            deviceRepository.deleteLabAndDeviceRelationship(pageLabVo.getId());
            deviceIdList.stream().forEach(d->{
                deviceRepository.insertLabAndDeviceRelationship(pageLabVo.getId(), d);
            });
        }else {
            labRepository.insertLab(pageLabVo);
            deviceIdList.stream().forEach(d->{
                deviceRepository.insertLabAndDeviceRelationship(pageLabVo.getId(), d);
            });
        }
    }
}
