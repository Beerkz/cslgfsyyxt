package com.cslg;

import com.cslg.system.SysRoleService;
import com.cslg.system.entity.SysRole;
import com.cslg.system.repository.SysRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SysRoleMapperTest {

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysRoleService sysRoleService;

    //1.查询表中所有数据
    @Test
    public void roleAdd() {
        //List<SysRole> sysRoles = sysRoleRepository.selectList(null);
        //sysRoles.forEach(System.out::println);
        List<SysRole> list = sysRoleService.list();
        for (SysRole sysRole : list) {
            System.out.println(sysRole);
        }
    }
}
