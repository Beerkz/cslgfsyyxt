package com.cslg.deivce.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.cslg.deivce.NeedDeviceService;
import com.cslg.deivce.entity.NeedDeviceEntity;
import com.cslg.deivce.param.PageNeedDeviceCondition;
import com.cslg.deivce.repository.NeedDeviceRepository;
import com.cslg.deivce.vo.DeviceVo;
import com.cslg.deivce.vo.NeedDeviceVo;
import com.cslg.vo.JsonPagedVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NeedDeviceImpl implements NeedDeviceService {
    private final NeedDeviceRepository needDeviceRepository;
    @Override
    public JsonPagedVO<NeedDeviceEntity> page(PageNeedDeviceCondition pageNeedDeviceCondition) {
        List<NeedDeviceEntity> deviceVos;
        Long countPageDevice = needDeviceRepository.countPageNeedDevice(pageNeedDeviceCondition);
        if (countPageDevice > 0) {
            deviceVos = needDeviceRepository.pageNeedDevice(pageNeedDeviceCondition);
        } else {
            deviceVos = Collections.emptyList();
        }
        return new JsonPagedVO<>(deviceVos, countPageDevice);
    }

    @Override
    public Integer insertOrUpdate(NeedDeviceVo needDeviceVo) {
        if (needDeviceVo.getId()!=null){
            needDeviceRepository.updateNeedDevice(needDeviceVo);
        }else {
            needDeviceRepository.insertNeedDevice(needDeviceVo);
        }
        return null;
    }

    @Override
    public NeedDeviceVo getDeviceInfo(Long id) {
        NeedDeviceVo needDeviceInfoVo = needDeviceRepository.getNeedDeviceInfoVo(id);
        needDeviceInfoVo.setUserList(needDeviceRepository.getProviderInfo(id));
        return needDeviceInfoVo;
    }

    @Override
    public Integer insertProviderInfo(Long needId) {
        //needDeviceRepository.insertNeedDeviceAndProviderRelationship(StpUtil.getLoginIdAsLong(), needId);
        return null;
    }

    @Override
    public Integer deleteNeedDevice(Long id) {
        needDeviceRepository.deleteNeedDevice(id);
        return null;
    }


}
