package com.hillstone.controller;

import com.hillstone.DeviceMagementApplication;
import com.hillstone.constant.Constants;
import com.hillstone.entity.vo.DeviceCustomerVo;
import com.hillstone.entity.vo.DeviceVo;
import com.hillstone.service.DeviceService;
import com.hillstone.util.R;
import com.hillstone.util.ResultUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class DeviceControllerTest {

    @InjectMocks
    private DeviceController deviceController;

    @Mock
    private DeviceService deviceService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddDevice() {

        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setSn("0000000012289208");
        deviceVo.setIp("10.12.10.51");
        deviceVo.setHostname("sv.hillstonenedt.cn");
        deviceVo.setCustomerId("000002");
        Mockito.when(deviceService.addDevice(deviceVo)).thenReturn(ResultUtil.result(Constants.SUCCESS,null,null));
        R r = deviceController.addDevice(deviceVo);
        assertThat(r,equalTo(ResultUtil.result(Constants.SUCCESS,null,null)));
    }

    @Test
    void testDeleteDevice() {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setSn("");
        Mockito.when(deviceService.deleteDevice(deviceVo.getSn())).thenReturn(ResultUtil.result(Constants.SUCCESS,null,null));
        R r = deviceController.deleteDevice(deviceVo);
        assertThat(r,equalTo(ResultUtil.result(Constants.SUCCESS,null,null)));
    }

    @Test
    void testUpdateDeviceInfo() {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setSn("000002");
        deviceVo.setIp("0.0.0.0");
        deviceVo.setCustomerId("000002");
        Mockito.when(deviceService.updateDeviceInfo(deviceVo)).thenReturn(ResultUtil.result(Constants.SUCCESS,null,null));
        R r = deviceController.updateDeviceInfo(deviceVo);
        assertThat(r,equalTo(ResultUtil.result(Constants.SUCCESS,null,null)));
    }

    @Test
    void testQueryDevice() {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setPage(2);
        deviceVo.setLimit(5);
        deviceVo.setStartTime(1689508501070L);
        deviceVo.setEndTime(new Date().getTime());
        deviceVo.setCustomerId("000000002");
        DeviceCustomerVo deviceCustomerVo;
        List<DeviceCustomerVo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            deviceCustomerVo = new DeviceCustomerVo();
            deviceCustomerVo.setCustomerName(i%2==0?"山石":"华为");
            deviceCustomerVo.setSn("000000001228920"+i);
            deviceCustomerVo.setHostname("hillstone"+i);
            deviceCustomerVo.setIp("192.168.145."+i);
            deviceCustomerVo.setCreateTime(new Date().getTime());
            deviceCustomerVo.setOnline(1);
            list.add(deviceCustomerVo);
        }
        Mockito.when(deviceService.queryPage(deviceVo)).thenReturn(ResultUtil.result(Constants.SUCCESS,list,null));
        R<List<DeviceCustomerVo>> listR = deviceController.queryDevice(deviceVo);
        System.out.println(listR.getData());
    }
}