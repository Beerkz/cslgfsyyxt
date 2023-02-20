package com.cslg.enums;

import lombok.Getter;

/**
 * 操作人类别
 */
@Getter
public enum OperatorType {
    /**
     * 其它
     */
    OTHER,

    /**
     * 后台用户
     */
    MANAGE,

    /**
     * 手机端用户
     */
    MOBILE
}
