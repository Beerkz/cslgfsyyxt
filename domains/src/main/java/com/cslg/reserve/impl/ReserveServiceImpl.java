package com.cslg.reserve.impl;

import cn.afterturn.easypoi.word.WordExportUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.cslg.reserve.FlowableService;
import com.cslg.reserve.ReserveService;
import com.cslg.reserve.param.AuditParam;
import com.cslg.reserve.param.PageReserveCondition;
import com.cslg.reserve.param.StartReserveParam;
import com.cslg.reserve.repository.ReserveRepository;
import com.cslg.reserve.vo.ReserveInfoVo;
import com.cslg.reserve.vo.ReserveLabVo;
import com.cslg.system.entity.SysUser;
import com.cslg.system.repository.SysUserRepository;
import com.cslg.vo.JsonPagedVO;
import lombok.AllArgsConstructor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.Group;
import org.flowable.task.api.Task;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;
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

    private final SysUserRepository sysUserRepository;

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
        //if (start) {
        //    reserveRepository.updateReserve(startReserveParam);
        //}
        return start;
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

    /**
     * 获取预约信息
     *
     * @param id 预约id
     * @return
     */
    @Override
    public ReserveInfoVo getMyReserveInfo(Long id) {
        ReserveInfoVo reserveInfoVo = reserveRepository.getMyReserveInfoById(id);
        HistoricProcessInstance historicProcessInstance = historyService
                .createHistoricProcessInstanceQuery()
                .processInstanceBusinessKey(reserveInfoVo.getProKey())
                .includeProcessVariables()
                .singleResult();
        Map<String, Object> processVariables = historicProcessInstance.getProcessVariables();
        if (processVariables.get("teacherReason") != null) {
            reserveInfoVo.setTeacherAuditReason(String.valueOf(processVariables.get("teacherReason")));
        }
        if (processVariables.get("managerReason") != null) {
            reserveInfoVo.setManagerAuditReason(String.valueOf(processVariables.get("managerReason")));
        }
        String stepName = (String) processVariables.get("stepName");
        reserveInfoVo.setStepName(stepName);
        return reserveInfoVo;
    }

    @Override
    public ResponseEntity<byte[]> exportReserve(Long id) {
        ReserveInfoVo reserveInfoVo = reserveRepository.getMyReserveInfoById(id);
        SysUser user = sysUserRepository.getUserById(reserveInfoVo.getUserId());
        Map<String, Object> detail = Map.of("id", String.valueOf(id),
                "name", user.getUsername(),
                "labName", reserveInfoVo.getLabName(),
                "spliceTime", reserveInfoVo.getSpliceTime(),
                "reserveDate", reserveInfoVo.getReserveDate(),
                "reason", reserveInfoVo.getReason()
        );
        try {

            File rootFile = new File((ResourceUtils.getURL("classpath:").getPath()));
            File templateFile = new File(rootFile, "/model/model.docx");
            XWPFDocument doc = WordExportUtil.exportWord07(
                    templateFile.getPath(),
                    detail);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            doc.write(byteArrayOutputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", "attachment; filename=" + "预约凭证");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Last-Modified", new Date().toString());
            headers.add("ETag", String.valueOf(System.currentTimeMillis()));
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_OCTET_STREAM).body(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
