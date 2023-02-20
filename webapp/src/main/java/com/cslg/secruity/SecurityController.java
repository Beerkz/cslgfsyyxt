package com.cslg.secruity;

import cn.dev33.satoken.stp.StpUtil;
import com.cslg.system.vo.LoginVo;
import com.cslg.vo.RestBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * @return token
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public RestBody<?> login(@RequestBody LoginVo loginVo) {
        String token = securityService.securityService(loginVo);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return RestBody.okData(map);
    }

    /**
     * 获取登录用户基本信息
     *
     * @return 用户权限
     */
    @GetMapping("/info")
    @ApiOperation("用户基本信息")
    public RestBody<?> getInfo() {
        Long loginId = StpUtil.getLoginIdAsLong();
        Map<String, Object> userInfo = securityService.getUserInfo(loginId);
        //Map<String, Object> map = new HashMap<>();
        //map.put("roles", "[admin]");
        //map.put("introduction", "i am a super administrator");
        //map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        //map.put("name", "Super Admin zkkk");
        return RestBody.okData(userInfo);
    }

    @PostMapping("/logout")
    @ApiOperation("用户登出")
    public RestBody<?> logout() {
        StpUtil.logout();
        return RestBody.ok();
    }
}
