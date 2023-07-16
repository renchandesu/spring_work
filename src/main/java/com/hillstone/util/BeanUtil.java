package com.hillstone.util;

import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BeanUtil {
    private BeanUtil(){}
    public static <T> T copyBean(Object source,Class<T> clazz) {
        T res = null;
        try {
            res = clazz.getConstructor().newInstance();
            BeanUtils.copyProperties(source, res);
        } catch (Exception e) {
            throw new InternalError("拷贝bean失败:"+e.getMessage());
        }
        return res;
    }

}
