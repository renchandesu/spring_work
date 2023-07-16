package com.hillstone.errcode;

import com.hillstone.constant.Constants;
import com.hillstone.util.R;
import com.hillstone.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(BindException.class)
    public R bindHandler(BindException bindException){
        List<ObjectError> allErrors = bindException.getAllErrors();
        StringBuilder sb = new StringBuilder();

        for (ObjectError allError : allErrors) {
            log.warn("参数校验错误信息，错误信息:{}", allError.getDefaultMessage());
            sb.append(allError.getDefaultMessage()).append(" ");
        }
        return ResultUtil.result(Constants.FAIL,null,sb.toString());
    }


    @ExceptionHandler(value = Exception.class)
    public R errorHandler(Exception e) {
        log.error("内部异常，未捕获："+e.getMessage());
        return ResultUtil.result(Constants.FAIL,null,e.getMessage());
    }

}
