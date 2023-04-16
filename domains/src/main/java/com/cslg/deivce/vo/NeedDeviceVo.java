package com.cslg.deivce.vo;

import com.cslg.deivce.entity.NeedDeviceEntity;
import com.cslg.system.entity.SysUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class NeedDeviceVo extends NeedDeviceEntity {

    List<SysUser> userList;
}
