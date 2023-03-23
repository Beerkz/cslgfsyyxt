package com.cslg.reserve.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.cslg.reserve.FlowableService;
import com.cslg.reserve.ReserveService;
import com.cslg.reserve.param.AuditParam;
import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveParam;
import com.cslg.reserve.repository.ReserveRepository;
import com.cslg.reserve.vo.ReserveLabVo;
import com.cslg.vo.JsonPagedVO;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.AllArgsConstructor;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.GroupQuery;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReserveServiceImpl implements ReserveService {

    private final FlowableService flowableService;

    private final ReserveRepository reserveRepository;

    private final IdentityService identityService;

    private final TaskService taskService;

    private final RuntimeService runtimeService;

    private final HistoryService historyService;

    @Override
    public List<ReserveLabVo> pageReserveLab(PageReserveCondition pageReserveCondition) {
        return null;
    }

    @Override
    public boolean startReserve(StartReserveParam startReserveParam) {
        String key = "RES" + IdUtil.simpleUUID();
        startReserveParam.setProKey(key);
        String formatReserveDate = DateUtil.format(DateUtil.parse(startReserveParam.getReserveDate()), "yyyy-MM-dd");
        startReserveParam.setReserveDate(formatReserveDate);
        startReserveParam.setUserId(StpUtil.getLoginIdAsLong());
        if (reserveRepository.countReserveByParam(startReserveParam) > 0) {
            throw new RuntimeException("您在此日期的时间段已经预约了其他实验室，请先取消，在重新预约！");
        }
        reserveRepository.insertReserve(startReserveParam);
        ////流程启动
        Boolean start = flowableService.startWorkFlow(startReserveParam);
        ////启动成功
        if (start) {
            reserveRepository.updateReserve(startReserveParam);
        }
        return true;
    }

    /**
     * 分页查寻当前登录用户的待处理流程任务
     *
     * @param pageReserveCondition
     * @return
     */
    @Override
    public JsonPagedVO<ReserveLabVo> pageAuditDoReserve(PageReserveCondition pageReserveCondition) {
        List<Group> list = identityService.createGroupQuery().groupMember(StpUtil.getLoginIdAsString()).list();
        List<String> ids = new ArrayList<>();
        //根据人物不同角色查询出不同的分组最后在查询所有的需要审批流程的id
        list.stream().forEach(group -> {
            List<Task> doTask = taskService.createTaskQuery()
                    .taskCandidateGroup(group.getId())
                    .list();
            List<String> reserveLabVoIds = doTask.stream().map(task -> {
                //根据任务id获取
                String processInstanceId = task.getProcessInstanceId();
                //获取流程实例
                ProcessInstance processInstance = runtimeService
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstanceId)
                        .includeProcessVariables()
                        .singleResult();

                //获取流程实例的key
                String businessKey = processInstance.getBusinessKey();
                return businessKey;
            }).collect(Collectors.toList());
            ids.addAll(reserveLabVoIds);
        });

        //根据查询条件和id获取
        Long count = reserveRepository.countReserveInfo(pageReserveCondition, ids);
        final List<ReserveLabVo> reserveLabVos;
        if (count > 0) {
            reserveLabVos = (reserveRepository.pageReserveInfo(pageReserveCondition, ids));
            //设置流程状态
            reserveLabVos.stream().forEach(item -> {
                Map<String, Object> processVariables = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(item.getProKey()).includeProcessVariables().singleResult().getProcessVariables();
                String stepName = (String) processVariables.get("stepName");
                item.setStatusName(stepName);
                item.setStatusCode((String) processVariables.get("step"));
            });
        } else {
            reserveLabVos = Collections.emptyList();
        }

        return new JsonPagedVO<>(reserveLabVos, count);
    }

    @Override
    public void auditReserve(AuditParam auditParam) {
        flowableService.completeTask(auditParam);
    }

    @Override
    public JsonPagedVO<ReserveLabVo> pageAuditMyReserve(PageReserveCondition pageReserveCondition) {
        List<ReserveLabVo> reserveLabVos;
        pageReserveCondition.setUserId(StpUtil.getLoginIdAsLong());
        Long count = reserveRepository.countMyReserveInfo(pageReserveCondition);
        if (count > 0) {
            reserveLabVos = reserveRepository.pageMyReserveInfo(pageReserveCondition);
        } else {
            reserveLabVos = Collections.emptyList();
        }
        return new JsonPagedVO<>(reserveLabVos, count);
    }
}
