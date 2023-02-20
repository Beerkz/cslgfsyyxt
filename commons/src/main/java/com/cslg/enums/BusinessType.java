package com.cslg.enums;

import lombok.*;

/**
 * 业务操作类型
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public enum BusinessType {
    /**
     * 其它
     */
    OTHER(0, "其他"),

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 修改
     */
    UPDATE(2, "修改"),

    /**
     * 删除
     */
    DELETE(3, "删除"),

    /**
     * 授权
     */
    ASSGIN(4, "授权"),

    /**
     * 导出
     */
    EXPORT(5, "导出"),

    /**
     * 导入
     */
    IMPORT(6, "导入"),

    /**
     * 强退
     */
    FORCE(7, "强退"),

    /**
     * 更新状态
     */
    STATUS(8, "跟新"),
    /**
     * 清空数据
     */
    CLEAN(9, "清空数据"),
    /**
     * 查询全部数据
     */
    LIST(10, "查询全部数据"),
    /**
     * 查看单个数据
     */
    VIEW(11, "查看信息"),
    ;
    private Integer Code;
    private String message;
}
