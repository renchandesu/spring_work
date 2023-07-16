package com.hillstone.entity.vo;

import lombok.Data;

@Data
public class DeviceVo {
    private String sn;
    private String ip;
    private String hostname;
    private String customerId;
    private Integer page;
    private Integer limit;
    private Long startTime;
    private Long endTime;
}
