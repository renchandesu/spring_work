package com.hillstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
public class DeviceMagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceMagementApplication.class, args);
    }

}
