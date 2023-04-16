package com.cslg.deivce.vo;

import lombok.Data;

@Data
public class NeedDeviceInfoVo extends NeedDeviceVo{
    private String username;
    private String email;

    private String phone;
}
