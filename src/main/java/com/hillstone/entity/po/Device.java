package com.hillstone.entity.po;

import lombok.Data;

@Data
public class Device {
    private String sn;
    private String ip;
    private String hostname;
    private Long createTime;
    private Long updateTime;
}
