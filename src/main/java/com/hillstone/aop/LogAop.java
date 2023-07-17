package com.hillstone.aop;

import com.alibaba.fastjson.JSONObject;
import com.hillstone.entity.vo.DeviceVo;
import com.hillstone.entity.vo.LogData;
import com.hillstone.errcode.exception.NoUsernameException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
@Aspect
@Slf4j
public class LogAop {

    @Pointcut(value = "execution(* com.hillstone.controller.DeviceController.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String method = request.getRequest().getMethod();
        String username = request.getRequest().getHeader("username");
        if (username == null){
            throw new NoUsernameException();
        }
        LogData logData = new LogData();
        logData.setOperator(username);
        logData.setTimestamp(new Date().getTime());
        Object[] args = joinPoint.getArgs();
        switch (method.toLowerCase()){
            case "get":
                logData.setOperation("查询设备 条件:"+args[0].toString());
                break;
            case "post":
                logData.setOperation("新增设备 :"+args[0].toString());
                break;
            case "put":
                logData.setOperation("修改设备信息 参数:"+args[0].toString());
                break;
            case "delete":
                logData.setOperation("删除设备 sn:"+((DeviceVo)args[0]).getSn());
                break;
        }
        log.info(JSONObject.toJSONString(logData));

    }
}
