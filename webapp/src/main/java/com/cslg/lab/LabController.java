package com.cslg.lab;

import com.cslg.lab.entity.LabSpliceTimeEntity;
import com.cslg.lab.param.PageLabCondition;
import com.cslg.lab.vo.LabSpliceTimeVo;
import com.cslg.lab.vo.LabVo;
import com.cslg.lab.vo.PageLabVo;
import com.cslg.vo.RestBody;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("secured/lab")
@AllArgsConstructor
public class LabController {
    private final LabService labService;


    @PostMapping("page")
    @ApiOperation("分页查询实验室")
    public RestBody<?> pageLab(@RequestBody PageLabCondition pageLabCondition) {
        return RestBody.okData(labService.pageLab(pageLabCondition));
    }

    @PostMapping("insert")
    @ApiOperation("实验室插入")
    public RestBody<?> insertLab(@RequestBody PageLabVo pageLabVo) {
        labService.insertOrUpdateLab(pageLabVo);
        return RestBody.ok();
    }

    @PostMapping("update")
    @ApiOperation("修改")
    public RestBody<?> updateLab(@RequestBody PageLabVo pageLabVo) {
        labService.insertOrUpdateLab(pageLabVo);
        return RestBody.ok();
    }

    @GetMapping("splicetime")
    @ApiOperation("实验室时间段")
    public RestBody<?> findSpliceTime() {
        List<LabSpliceTimeVo> labSpliceTimeEntities = labService.selectSpliceTime();

        return RestBody.okData(labSpliceTimeEntities);
    }

    @GetMapping("lab/info/{id}")
    @ApiOperation("获取实验室信息")
    public RestBody<?> getLabInfo(@PathVariable Long id) {
        LabVo labInfo = labService.getLabInfo(id);
        return RestBody.okData(labInfo);

    }

    @GetMapping("delete/{id}")
    @ApiOperation("实验室删除")
    public RestBody<?> deleteLabInfo(@PathVariable Long id) {
        labService.deleteInfo(id);
        return RestBody.ok();
    }

    @PostMapping("all/lab")
    @ApiOperation("实验室信息")
    public RestBody<?> getAllLab() {
        List<LabVo> allLab = labService.getAllLab();
        return RestBody.okData(allLab);
    }
}
