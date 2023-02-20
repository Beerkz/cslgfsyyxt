package com.cslg.exception;

import cn.dev33.satoken.exception.NotPermissionException;
import com.cslg.vo.RestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常捕捉
     *
     * @param e
     * @return RestBody
     */
    @ExceptionHandler(Exception.class)
    public RestBody<?> exceptionHandler(Exception e) {
        log.error("全局异常抓捕:{}", e);
        return RestBody.failure(e.getMessage(), "fail");
    }

    /**
     * 特定异常优先捕获，指定名称
     *
     * @param e
     * @return RestBody
     */
    @ExceptionHandler(ArithmeticException.class)
    public RestBody<?> arithmeticException(ArithmeticException e) {
        log.error("特定异常抓捕:{}", e);
        return RestBody.failure(e.getMessage(), "fail");
    }

    @ExceptionHandler(AffairsException.class)
    public RestBody<?> affairsException(AffairsException e) {
        log.error("自定义事务异常:{}", e);

        return RestBody.failure(e.getCode(), e.getMsg(), "fail");
    }

    @ExceptionHandler(NotPermissionException.class)
    public RestBody<?> notPermissionException(NotPermissionException e) {
        log.error("权限异常：{}", e.getMessage());
        return RestBody.failure(e.getMessage(), "fail");
    }
}
