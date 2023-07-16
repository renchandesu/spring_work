package com.hillstone.constant;

import org.springframework.data.relational.core.sql.In;

public class Constants {
    public static final String TOPIC_NAME = "heartbeat";

    public static final String GROUP_ID = "co1";
    public static final String DEVICE_ON = "1";
    public static final String DEVICE_OFF = "0";

    public static final Integer EXPIRE_TIME = 6*60*1000; //ms

    public static final Integer SUCCESS = 1;
    public static final Integer FAIL = 0;

}
