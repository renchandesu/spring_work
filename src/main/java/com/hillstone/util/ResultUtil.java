package com.hillstone.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResultUtil {

    public <T> R<T> result(Integer code, T data,String msg){
        R<T> res = new R<T>();
        res.setStatus(code);
        res.setData(data);
        res.setError(msg);
        return res;
    }

}
