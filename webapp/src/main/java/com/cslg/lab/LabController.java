package com.cslg.lab;

import com.cslg.lab.param.PageLabCondition;
import com.cslg.lab.vo.PageLabVo;
import com.cslg.vo.RestBody;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    @ApiOperation("insert")
    public RestBody<?> insertLab(@RequestBody PageLabVo pageLabVo){
        return RestBody.ok();
    }
}
