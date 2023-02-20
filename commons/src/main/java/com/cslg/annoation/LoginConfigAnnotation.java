package com.cslg.annoation;

import com.cslg.enums.BusinessType;
import com.cslg.enums.OperatorType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Documented
public @interface LoginConfigAnnotation {
    /**
     * 模块名称
     */
    String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;
}
