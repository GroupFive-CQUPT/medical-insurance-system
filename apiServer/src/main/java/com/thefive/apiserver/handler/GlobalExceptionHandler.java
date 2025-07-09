package com.thefive.apiserver.handler;

import com.thefive.apiserver.utils.ResultVo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * JWT签名异常处理
     */
    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public ResultVo handleSignatureException(SignatureException e) {
        return ResultVo.fail("Token签名异常，请重新登录", "token_error");
    }

    /**
     * JWT格式异常处理
     */
    @ExceptionHandler(MalformedJwtException.class)
    @ResponseBody
    public ResultVo handleMalformedJwtException(MalformedJwtException e) {
        return ResultVo.fail("Token格式异常，请重新登录", "token_error");
    }

    /**
     * JWT过期异常处理
     */
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public ResultVo handleExpiredJwtException(ExpiredJwtException e) {
        return ResultVo.fail("登录已过期，请重新登录", "token_error");
    }

    /**
     * 通用异常处理
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo handleException(Exception e) {
        if (e.getMessage().contains("token")) {
            return ResultVo.fail(e.getMessage(), "token_error");
        }
        return ResultVo.fail("系统异常: " + e.getMessage());
    }
}