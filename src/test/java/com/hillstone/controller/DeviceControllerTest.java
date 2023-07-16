package com.hillstone.controller;

import com.hillstone.entity.vo.DeviceVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeviceControllerTest {

    @Autowired
    private DeviceController deviceController;

    @Test
    void sendHeartByKafka() {
        deviceController.sendHeartByKafka("000001",1);
    }

    @Test
    void addDevice() {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setSn("000005");
        deviceVo.setIp("10.12.10.51");
        deviceVo.setHostname("sv.hillstonenedt.cn");
        deviceVo.setCustomerId("000002");
        deviceController.addDevice(deviceVo);
    }

    @Test
    void deleteDevice() {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setSn("");
        deviceController.deleteDevice(deviceVo);
    }

    @Test
    void updateDeviceInfo() {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setSn("000002");
        deviceVo.setIp("0.0.0.0");
        deviceVo.setCustomerId("000002");
        deviceController.updateDeviceInfo(deviceVo);
    }

    @Test
    void queryDevice() {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setPage(2);
        deviceVo.setLimit(5);
        deviceVo.setStartTime(1689508501070L);
        deviceVo.setEndTime(new Date().getTime());
        deviceVo.setCustomerId("000000002");
        System.out.println(deviceController.queryDevice(deviceVo));
    }
}