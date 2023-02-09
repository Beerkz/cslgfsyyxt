package com.cslg.system.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginVo {
    /**
     * 用户名
     */
    private String username;
    /**
     * 学号
     */
    private String stuNo;
    /**
     * 密码
     */
    private String password;
}
