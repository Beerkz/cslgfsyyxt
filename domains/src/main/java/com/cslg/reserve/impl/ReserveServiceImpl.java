package com.cslg.reserve.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.cslg.reserve.FlowableService;
import com.cslg.reserve.ReserveService;
import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveParam;
import com.cslg.reserve.repository.ReserveRepository;
import com.cslg.reserve.vo.ReserveLabVo;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.AllArgsConstructor;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.GroupQuery;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReserveServiceImpl implements ReserveService {

    private final FlowableService flowableService;

    private final ReserveRepository reserveRepository;

    private final IdentityService identityService;

    private final TaskService taskService;

    private final RuntimeService runtimeService;

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
        if (reserveRepository.countReserveByParam(startReserveParam) > 0) {
            throw new RuntimeException("您在此日期的时间段已经预约了其他实验室，请先取消，在重新预约！");
        }
        reserveRepository.insertReserve(startReserveParam);
        ////流程启动
        Boolean start = flowableService.startWorkFlow(key);
        ////启动成功
        if (start) {
            reserveRepository.updateReserve(startReserveParam);
        }
        return true;
    }

    @Override
    public List<ReserveLabVo> auditReserve(PageReserveCondition pageReserveCondition) {
        Group group = identityService.createGroupQuery().groupMember(StpUtil.getLoginIdAsString()).singleResult();
        List<Task> doTask = taskService.createTaskQuery().taskCandidateGroup(group.getId()).list();
        doTask.stream().forEach(task -> {
            //根据任务id获取
            String processInstanceId = task.getProcessInstanceId();
            //获取流程实例
            ProcessInstance processInstance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            //获取流程实例的key
            String businessKey = processInstance.getBusinessKey();

        });
        return null;
    }
}
