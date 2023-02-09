package com.cslg.secruity;

import com.cslg.system.vo.LoginVo;

import java.util.Map;

public interface SecurityService {

    /**
     * 用户登录验证
     *
     * @param loginVo
     * @return
     */
    String securityService(LoginVo loginVo);

    /**
     * 获取用户信息
     */
    Map<String, Object> getUserInfo(Long id);
}
