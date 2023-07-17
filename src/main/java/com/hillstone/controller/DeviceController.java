package com.hillstone.controller;

import com.hillstone.entity.vo.DeviceCustomerVo;
import com.hillstone.entity.vo.DeviceVo;
import com.hillstone.service.DeviceService;
import com.hillstone.util.R;
import com.hillstone.validate.DeleteGroup;
import com.hillstone.validate.InsertGroup;
import com.hillstone.validate.QueryGroup;
import com.hillstone.validate.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {


    @Autowired
    DeviceService deviceService;

    @PostMapping("/")
    public R addDevice(@RequestBody @Validated(value = InsertGroup.class) DeviceVo deviceVo){
        return deviceService.addDevice(deviceVo);
    }

    @DeleteMapping("/")
    public R deleteDevice(@RequestBody @Validated(value = DeleteGroup.class) DeviceVo deviceVo){
        return deviceService.deleteDevice(deviceVo.getSn());
    }

    @PutMapping("/")
    public R updateDeviceInfo(@RequestBody @Validated(value = UpdateGroup.class) DeviceVo deviceVo){
        return deviceService.updateDeviceInfo(deviceVo);
    }

    @GetMapping("/")
    public R<List<DeviceCustomerVo>> queryDevice(@Validated(value = QueryGroup.class) DeviceVo deviceVo){
        return deviceService.queryPage(deviceVo);
    }




}
