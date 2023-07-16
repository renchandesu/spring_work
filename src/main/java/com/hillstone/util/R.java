package com.hillstone.util;

import lombok.Data;

@Data
public class R<T> {
    Integer status;
    String error;
    T data;
}
