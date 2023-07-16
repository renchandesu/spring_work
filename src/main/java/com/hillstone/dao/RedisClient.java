package com.hillstone.dao;

import com.hillstone.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void setStatus(String sn,Integer online){
        if (online == 1){
            stringRedisTemplate.opsForValue().set(sn, Constants.DEVICE_ON,Constants.EXPIRE_TIME, TimeUnit.MILLISECONDS);
            return;
        }
        // 如果状态不是在线，则将redis中的sn的值设置为0 并且不需要加过期时间
        stringRedisTemplate.opsForValue().set(sn,Constants.DEVICE_OFF);
    }

    public String getStatus(String sn){
        String res = stringRedisTemplate.opsForValue().get(sn);
        return res == null ? "0" : res;
    }

}
