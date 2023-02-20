package com.cslg.secruity.config;

import cn.dev33.satoken.stp.StpInterface;
import com.cslg.system.entity.SysMenu;
import com.cslg.system.repository.SysMenuRepository;
import com.cslg.system.repository.SysRoleMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SaToken注解式鉴权配置类
 */
@Configuration
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final SysMenuRepository sysMenuRepository;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<SysMenu> menuByRoleId = sysMenuRepository.findMenuByRoleId(Long.valueOf((String) loginId));
        List<String> collect = menuByRoleId.stream().map(m -> String.valueOf(m.getPerms())).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}
