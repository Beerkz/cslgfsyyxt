package com.cslg.lab.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.cslg.deivce.entity.DeviceEntity;
import com.cslg.deivce.repository.DeviceRepository;
import com.cslg.lab.LabService;
import com.cslg.lab.entity.LabEntity;
import com.cslg.lab.entity.LabSpliceTimeEntity;
import com.cslg.lab.param.PageLabCondition;
import com.cslg.lab.repository.LabRepository;
import com.cslg.lab.vo.LabSpliceTimeVo;
import com.cslg.lab.vo.LabVo;
import com.cslg.lab.vo.PageLabVo;
import com.cslg.vo.JsonPagedVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    /**
     * 分页查询实验室信息
     *
     * @param pageLabCondition 分页查询条件
     * @return
     */
    @Override
    public JsonPagedVO<PageLabVo> pageLab(PageLabCondition pageLabCondition) {
        final List<PageLabVo> pageLabVos;
        Long count = labRepository.countPage(pageLabCondition);
        if (count > 0) {
            pageLabVos = labRepository.page(pageLabCondition);

        } else {
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
        pageLabVo.setUpdateId(StpUtil.getLoginIdAsInt());
        if (pageLabVo.getId() != null) {
            labRepository.updateLab(pageLabVo);
            labRepository.deleteSpliceTimeById(pageLabVo.getId());
            labRepository.insertSpliceTime(pageLabVo.getSpliceTimeIds(), pageLabVo.getId());
            //deviceIdList.stream().forEach(d->{
            //    deviceRepository.insertLabAndDeviceRelationship(pageLabVo.getId(), d);
            //});
        } else {
            labRepository.insertLab(pageLabVo);
            labRepository.insertSpliceTime(pageLabVo.getSpliceTimeIds(), pageLabVo.getId());
            //deviceIdList.stream().forEach(d->{
            //    deviceRepository.insertLabAndDeviceRelationship(pageLabVo.getId(), d);
            //});
        }
    }

    /**
     * 获取实验室开放的时间段信息
     *
     * @return
     */
    @Override
    public List<LabSpliceTimeVo> selectSpliceTime() {
        List<LabSpliceTimeEntity> allSpliceTime = labRepository.getAllSpliceTime();
        List<LabSpliceTimeVo> collect = allSpliceTime.stream().map(item -> {
            LabSpliceTimeVo labSpliceTimeVo = new LabSpliceTimeVo();
            BeanUtils.copyProperties(item, labSpliceTimeVo);
            labSpliceTimeVo.setSpliceTime(item.getBeginTime() + "-" + item.getEndTime());
            return labSpliceTimeVo;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 获取单个实验室的信息
     *
     * @param id 实验室id
     * @return
     */
    @Override
    public LabVo getLabInfo(Long id) {
        LabVo labInfo = labRepository.getLabInfo(id);
        return labInfo;
    }

    /**
     * 删除实验室
     *
     * @param id
     */
    @Override
    public void deleteInfo(Long id) {
        labRepository.deleteLabById(id);
    }

    /**
     * 获取所有实验室信息
     *
     * @return
     */
    @Override
    public List<LabVo> getAllLab() {
        List<LabVo> allLab = labRepository.getAllLab();
        return allLab;
    }
}
