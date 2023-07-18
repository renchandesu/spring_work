package com.hillstone.service;

import com.hillstone.dao.DeviceDao;
import com.hillstone.dao.RedisClient;
import com.hillstone.entity.vo.DeviceCustomerVo;
import com.hillstone.entity.vo.DeviceVo;
import com.hillstone.util.R;
import com.hillstone.util.ResultUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hillstone.constant.Constants.SUCCESS;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;


public class DeviceServiceTest {

    @Mock
    private DeviceDao deviceDao;

    @Mock
    private RedisClient redisClient;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddDevice(){
        DeviceVo device = new DeviceVo();
        device.setSn("3F5R7G2H8J0K1L9Z");
        device.setCustomerId("000000001");
        R successResult = deviceService.addDevice(device);
        assertThat(successResult,equalTo(ResultUtil.result(SUCCESS,null,null)));



    }

    @Test
    public void testDeleteDevice(){
        R successResult = deviceService.deleteDevice("3F5R7G2H8J0K1L9Z");
        assertThat(successResult,equalTo(ResultUtil.result(SUCCESS,null,null)));
    }

    @Test
    public void testUpdateDeviceInfo(){
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setSn("3F5R7G2H8J0K1L9Z");
        deviceVo.setIp("0.0.0.0");
        deviceVo.setHostname("new test");
        R successResult = deviceService.updateDeviceInfo(deviceVo);
        assertThat(successResult,equalTo(ResultUtil.result(SUCCESS,null,null)));

    }

    @Test
    public void testQueryPage(){
        DeviceVo query = new DeviceVo();
        DeviceCustomerVo deviceCustomerVo;
        List<DeviceCustomerVo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            deviceCustomerVo = new DeviceCustomerVo();
            deviceCustomerVo.setCustomerName(i%2==0?"山石":"华为");
            deviceCustomerVo.setSn("000000001228920"+i);
            deviceCustomerVo.setHostname("hillstone"+i);
            deviceCustomerVo.setIp("192.168.145."+i);
            deviceCustomerVo.setCreateTime(new Date().getTime());
            list.add(deviceCustomerVo);
        }
        Mockito.when(deviceDao.queryDevicePage(query)).thenReturn(list);
        Mockito.when(redisClient.getStatus(org.mockito.ArgumentMatchers.any(String.class))).thenReturn("1");
        R<List<DeviceCustomerVo>> listR = deviceService.queryPage(query);
        System.out.println(listR.getData());

    }



}
