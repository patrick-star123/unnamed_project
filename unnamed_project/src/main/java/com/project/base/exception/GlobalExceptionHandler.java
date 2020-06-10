package com.project.base.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by 小白 on 2020/5/31.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponse customException(Exception e){
        GlobalException globalException = (GlobalException) e;
        return new ExceptionResponse(globalException.getCode(), globalException.getMessage());
    }
}
