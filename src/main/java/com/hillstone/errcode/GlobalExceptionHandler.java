package com.hillstone.errcode;

import com.hillstone.constant.Constants;
import com.hillstone.util.R;
import com.hillstone.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {





    @ExceptionHandler(value = Exception.class)
    public R errorHandler(Exception e) {
        log.error("内部异常，未捕获："+e.getMessage());
        return ResultUtil.result(Constants.FAIL,null,e.getMessage());
    }

}
