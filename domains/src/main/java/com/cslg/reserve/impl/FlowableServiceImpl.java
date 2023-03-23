package com.cslg.reserve.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.cslg.reserve.FlowableService;
import com.cslg.reserve.param.AuditParam;
import com.cslg.reserve.param.StartReserveParam;
import com.cslg.reserve.repository.ReserveRepository;
import com.cslg.system.SysRoleService;
import com.cslg.system.SysUserService;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cslg.reserve.enums.ReserveEnums.*;
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

    private final ReserveRepository reserveRepository;

    //启动一个流程
    @Override
    public Boolean startWorkFlow(StartReserveParam key) {
        //跟新流程表中的老师信息
        //assignGroupTeacher();
        //更新流程表中的
        //assignGroupManager();
        //第一个参数是流程的启动id（key），第二个参数叫做流程的key需要唯一，第三个参数放流程的需要用的的变量map集合
        ProcessInstance reserveLabTest1 = runtimeService
                .startProcessInstanceByKey("reserveLabTest1",
                        key.getProKey(),
                        Map.of("mine", StpUtil.getLoginId(),
                                INSTRUCTOR.getGroupId(), identityService.createGroupQuery().groupId(INSTRUCTOR.getId()).singleResult().getId(),
                                MANAGER.getGroupId(), identityService.createGroupQuery().groupId(MANAGER.getId()).singleResult().getId()
                        )

                );
        //自己查询获取任务并且完成
        Task task = taskService.createTaskQuery().processInstanceId(reserveLabTest1.getProcessInstanceId()).singleResult();
        taskService.complete(task.getId(), Map.of(
                "step", AUDIT_TEACHER.getStep(),
                "stepName", AUDIT_TEACHER.getStepName(),
                "nextStep", AUDIT_MANAGER.getNexStep(),
                "nextStepName", AUDIT_MANAGER.getStepName(),
                "labId", key.getLabId(),
                "userId", key.getUserId(),
                "reserveDate", key.getReserveDate()));
        return true;
    }

    @Override
    public boolean completeTask(AuditParam auditParam) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(auditParam.getProKey()).includeProcessVariables().singleResult();
        Map<String, Object> processVariables = task.getProcessVariables();
        if (processVariables.get("step").equals("s02")) {
            //审批不通过
            if (auditParam.getResult().equals(1L)) {
                taskService.complete(task.getId(), Map.of(
                        "step", "auditEnd",
                        "stepName", "审核不通过",
                        "nextStep", "auditEnd",
                        "nextStepName", "审核不通过",
                        "teacherReason", auditParam.getReason(),
                        "instructorAudit", auditParam.getResult()
                ));
                reserveRepository.updateReserveStatus(auditParam.getId(), 1L);
            } else {
                taskService.complete(task.getId(), Map.of(
                        "step", AUDIT_MANAGER.getStep(),
                        "stepName", AUDIT_MANAGER.getStepName(),
                        "nextStep", "success",
                        "nextStepName", "success",
                        "teacherReason", auditParam.getReason(),
                        "instructorAudit", auditParam.getResult()
                ));
            }
        }
        if (processVariables.get("step").equals("s03")) {
            if (auditParam.getResult().equals(1L)) {
                taskService.complete(task.getId(), Map.of(
                        "step", "auditEnd",
                        "stepName", "审核不通过",
                        "nextStep", "auditEnd",
                        "nextStepName", "审核不通过",
                        "managerReason", auditParam.getReason(),
                        "instructorAudit", auditParam.getResult()

                ));
                reserveRepository.updateReserveStatus(auditParam.getId(), 1L);
            } else {
                taskService.complete(task.getId(), Map.of(
                        "step", "success",
                        "stepName", "审核通过",
                        "nextStep", "success",
                        "nextStepName", "审核通过",
                        "mangerReason", auditParam.getReason(),
                        "instructorAudit", auditParam.getResult()
                ));
                reserveRepository.updateReserveStatus(auditParam.getId(), 2L);

            }
        }
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
