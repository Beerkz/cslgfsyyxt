package com.cslg.secruity;

import cn.dev33.satoken.stp.StpUtil;
import com.cslg.system.vo.LoginVo;
import com.cslg.vo.RestBody;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录接口")
@RestController
@RequestMapping("system/secured")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;

    /**
     * 用户登录
     *
     * @param loginVo 用户登录条件
     * @return
     */
    @PostMapping("/login")
    public RestBody<?> login(LoginVo loginVo) {
        String token = securityService.securityService(loginVo);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return RestBody.okData(map);
    }

    @GetMapping("/info")
    public RestBody<?> logout() {
        Long loginId = (Long) StpUtil.getLoginId();
        Map<String, Object> map = new HashMap<>();
        map.put("roles", "[admin]");
        map.put("introduction", "i am a super administrator");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Super Admin zkkk");
        return RestBody.okData(map);
    }
}
