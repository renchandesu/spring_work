package com.hillstone.errcode;

import com.hillstone.constant.Constants;
import com.hillstone.errcode.exception.NoUsernameException;
import com.hillstone.util.R;
import com.hillstone.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(NoUsernameException.class)
    public R usernameHandler(NoUsernameException e){
        log.info("请求未携带username！");
        return ResultUtil.result(Constants.FAIL,null,e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public R bindHandler(HttpServletRequest request, BindException bindException){
        List<ObjectError> allErrors = bindException.getAllErrors();
        StringBuilder sb = new StringBuilder();

        for (ObjectError allError : allErrors) {
            log.info(allError.getCode());
            String info = getI18nMessage(allError.getDefaultMessage(),request);
            log.warn("参数校验错误信息，错误信息:{}", getI18nMessage(allError.getDefaultMessage(),request));
            sb.append(info).append(" ");
        }
        return ResultUtil.result(Constants.FAIL,null,sb.toString());
    }


    @ExceptionHandler(value = Exception.class)
    public R errorHandler(Exception e) {
        log.error("内部异常，未捕获："+e.getMessage());
        return ResultUtil.result(Constants.FAIL,null,e.getMessage());
    }


    private String getI18nMessage(String key, HttpServletRequest request) {
        try {
            return messageSource.getMessage(key, null, Locale.forLanguageTag(request.getHeader("Accept-Language")));
        } catch (Exception e) {
            // log
            log.error(e.getMessage()+" key:"+key,e);
            return key;
        }
    }

}
