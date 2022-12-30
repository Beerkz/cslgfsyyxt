package com.cslg.secruity;

import com.cslg.system.entity.SysUser;
import com.cslg.vo.RestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录接口")
@RestController
@RequestMapping("system/secured")
public class SecurityController {

    @PostMapping("/login")
    public RestBody<?> login(SysUser sysUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return RestBody.okData(map);
    }

    @GetMapping("/info")
    public RestBody<?> logout(SysUser sysUser) {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("introduction", "i am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super Admin zkkk");
        return RestBody.okData(map);
    }
}
