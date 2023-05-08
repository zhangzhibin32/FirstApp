package com.zzb.system.exception;

import com.zzb.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");


    }
    @ExceptionHandler(ZBException.class)
    @ResponseBody
    public Result error(ZBException e){
        e.printStackTrace();
        return Result.fail().code(e.getCode()).message(e.getMsg());

    }
}
