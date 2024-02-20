package com.pharos.common.advice;


import com.pharos.common.exception.BizException;
import com.pharos.common.response.BaseCode;
import com.pharos.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;


@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Response<Void> handleBizException(BizException e) {
        log.warn(e.getMsg());
        return new Response<Void>().error(e.getCode(), e.getMsg());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return new Response<Void>().error(BaseCode.PARAM_ERROR.getCode(), message);
    }
}
