package com.be.config;

import com.be.constanst.SystemConstant;
import com.be.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDTO<?> TodoException(Exception ex, WebRequest request) {
        return new ResponseDTO<>(false, SystemConstant.MSG_SYSTEM_ERROR);
    }
}
