package com.cslg.reserve.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.cslg.lab.FlowableService;
import com.cslg.system.SysRoleService;
import com.cslg.system.SysUserService;
import com.cslg.system.entity.BaseEntity;
import com.cslg.system.entity.SysRole;
import com.cslg.system.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.cslg.system.enums.RoleCode.INSTRUCTOR;
import static com.cslg.system.enums.RoleCode.MANAGER;

@Service
@AllArgsConstructor
@Slf4j
public class FlowableServiceImpl implements FlowableService {

    private final RuntimeService runtimeService;

    private final IdentityService identityService;

    private final SysUserService sysUserService;

    private final SysRoleService sysRoleService;

    private final TaskService taskService;


    //启动一个流程
    @Override
    public Boolean startWorkFlow() {
        //跟新流程表中的老师信息
        //assignGroupTeacher();
        //更新流程表中的
        //assignGroupManager();
        //第一个参数是流程的启动id（key），第二个参数叫做流程的key需要唯一，第三个参数放流程的需要用的的变量map集合
        String key = "RES" + IdUtil.simpleUUID();
        ProcessInstance reserveLabTest1 = runtimeService
                .startProcessInstanceByKey("reserveLabTest1",
                        key,
                        Map.of("mine", StpUtil.getLoginId(),
                                INSTRUCTOR.getGroupId(), identityService.createGroupQuery().groupId(INSTRUCTOR.getId()).singleResult(),
                                MANAGER.getGroupId(), identityService.createGroupQuery().groupId(MANAGER.getId()).singleResult()));
        Task task = taskService.createTaskQuery().processInstanceId(reserveLabTest1.getProcessInstanceId()).singleResult();
        taskService.complete(task.getId());
        return true;
    }

    /**
     * 更新辅导员组分配人信息
     */
    //@Scheduled()
    public void assignGroupTeacher() {
        Group instructor = identityService.createGroupQuery().groupId(INSTRUCTOR.getGroupId()).singleResult();

        if (instructor != null) {
            //角色为老师的用户
            List<SysUser> userByRoleCode = sysUserService.getUserByRoleCode(INSTRUCTOR.getRoleCode());
            log.info("角色为老师的用户:{}", userByRoleCode);
            //流程表中存在老师的用户
            List<User> iUser = identityService.createUserQuery().userLastName(INSTRUCTOR.getRoleCode()).list();
            Map<String, Object> collectUser = iUser.stream().collect(Collectors.toMap(User::getId, i -> i));
            log.info("流程表中的用户集合");
            userByRoleCode.stream().forEach(u -> {
                if (!collectUser.containsKey(String.valueOf(u.getId()))) {
                    User user = identityService.newUser(String.valueOf(u.getId()));
                    user.setFirstName(u.getName());
                    user.setPassword(u.getPassword());
                    user.setLastName(INSTRUCTOR.getRoleCode());
                    identityService.saveUser(user);
                    identityService.createMembership(user.getId(), instructor.getId());
                }
            });
        }
    }

    /**
     * 跟新实验室管理员信息
     */
    public void assignGroupManager() {
        Group manager = identityService.createGroupQuery().groupId(MANAGER.getGroupId()).singleResult();

        if (manager != null) {
            //角色为实验室负责人的用户
            List<SysUser> userByRoleCode = sysUserService.getUserByRoleCode(MANAGER.getRoleCode());
            log.info("角色为老师的用户:{}", userByRoleCode);
            //流程表中存在实验室负责人的用户
            List<User> iUser = identityService.createUserQuery().userLastName(MANAGER.getRoleCode()).list();
            Map<String, Object> collectUser = iUser.stream().collect(Collectors.toMap(User::getId, i -> i));
            log.info("流程表中的用户集合");
            userByRoleCode.stream().forEach(u -> {
                if (!collectUser.containsKey(String.valueOf(u.getId()))) {
                    User user = identityService.newUser(String.valueOf(u.getId()));
                    user.setFirstName(u.getName());
                    user.setPassword(u.getPassword());
                    user.setLastName(INSTRUCTOR.getRoleCode());
                    identityService.saveUser(user);
                    identityService.createMembership(user.getId(), manager.getId());
                }
            });
        }
    }
}
