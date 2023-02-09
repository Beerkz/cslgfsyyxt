package com.cslg.system.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ApiModel
@Getter
@Setter
@ToString
public class AssignMenuVo {
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 菜单id列表
     */
    private List<Long> menuIdLists;
}
