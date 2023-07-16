package com.hillstone.entity.po;

import lombok.Data;

@Data
public class Customer {

    private String customerId;
    private String customerName;
    private Long createTime;
    private Long updateTime;

}
