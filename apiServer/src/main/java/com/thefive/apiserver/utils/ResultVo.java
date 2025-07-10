package com.thefive.apiserver.utils;

import lombok.Data;

@Data
public class ResultVo<T> {
    private boolean flag;
    private String message;
    private T data;

    @SuppressWarnings("rawtypes")
    
    /**
     * 成功响应（仅消息）
     */
    public static ResultVo ok(String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setMessage(message);
        resultVo.setFlag(true);
        return resultVo;
    }

    /**
     * 成功响应（带数据）
     */
    public static <T> ResultVo ok(T data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(data);
        resultVo.setFlag(true);
        return resultVo;
    }

    /**
     * 成功响应（数据和消息）
     */
    public static <T> ResultVo ok(T data, String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(data);
        resultVo.setFlag(true);
        resultVo.setMessage(message);
        return resultVo;
    }

    /**
     * 失败响应
     */
    public static ResultVo fail(String message) {
        ResultVo resultVo = new ResultVo();
        resultVo.setFlag(false);
        resultVo.setMessage(message);
        return resultVo;
    }

    /**
     * 失败响应（带数据）
     */
    public static <T> ResultVo fail(String message, T data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setFlag(false);
        resultVo.setMessage(message);
        resultVo.setData(data);
        return resultVo;
    }

    /**
     * 异常响应
     */
    public static ResultVo error(Exception e) {
        ResultVo resultVo = new ResultVo();
        resultVo.setMessage("系统异常: " + e.getMessage());
        resultVo.setFlag(false);
        return resultVo;
    }
}