package com.hillstone.entity.vo;

import lombok.Data;

@Data
public class LogData {
    private String operation;
    private String operator;
    private Long timestamp;
}
