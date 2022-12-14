package com.cslg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestBody<T> {
    /**
     * 响应状态码
     */
    private int code = 200;
    /**
     * 响应状态描述
     */
    private String msg = "ok";
    /**
     * 返回数据
     */
    private T data;
    /**
     * 系统标识
     */
    private String identifier = "success";

    public static RestBody<Void> ok() {
        return new RestBody<>();
    }

    public static RestBody<Void> ok(String msg) {
        final RestBody<Void> restBody = new RestBody<>();
        restBody.setIdentifier(msg);
        return restBody;
    }

    public static <T> RestBody<T> okData(T data) {
        final RestBody<T> restBody = new RestBody<>();
        restBody.setData(data);
        return restBody;
    }

    public static <T> RestBody<T> okData(T data, String msg) {
        final RestBody<T> restBody = new RestBody<>();
        restBody.setData(data);
        restBody.setMsg(msg);
        return restBody;
    }

    public static <T> RestBody<T> build(int code, String msg, T data, String identifier) {
        final RestBody<T> restBody = new RestBody<>();
        restBody.setCode(code);
        restBody.setMsg(msg);
        restBody.setData(data);
        restBody.setIdentifier(identifier);
        return restBody;
    }


    public static RestBody<Void> failure(String msg, String identifier) {
        final RestBody<Void> restBody = new RestBody<>();
        restBody.setMsg(msg);
        restBody.setIdentifier(identifier);
        return restBody;
    }

    public static RestBody<Void> failure(int code, String msg) {
        final RestBody<Void> restBody = new RestBody<>();
        restBody.setCode(code);
        restBody.setMsg(msg);
        return restBody;
    }

    public static <T> RestBody<T> failureData(int code, T data, String msg, String identifier) {
        final RestBody<T> restBody = new RestBody<>();
        restBody.setCode(code);
        restBody.setData(data);
        restBody.setMsg(msg);
        restBody.setIdentifier(identifier);
        return restBody;
    }


}
