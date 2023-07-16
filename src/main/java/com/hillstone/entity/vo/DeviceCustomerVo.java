package com.hillstone.entity.vo;

import lombok.Data;

@Data
public class DeviceCustomerVo {
    private String sn;
    private String customerName;
    private String hostname;
    private String ip;
    private Integer online;
    private Long createTime;
}
