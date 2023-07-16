package com.hillstone.controller;

import com.hillstone.entity.po.Device;
import com.hillstone.entity.vo.DeviceCustomerVo;
import com.hillstone.entity.vo.DeviceVo;
import com.hillstone.kafka.KafkaProducer;
import com.hillstone.service.DeviceService;
import com.hillstone.util.R;
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
    KafkaProducer kafkaProducer;
    @Autowired
    DeviceService deviceService;

    @PostMapping("/")
    public R addDevice(@RequestBody @Validated(value = InsertGroup.class) DeviceVo deviceVo){
        return deviceService.addDevice(deviceVo);
    }

    @DeleteMapping("/")
    public R deleteDevice(@RequestBody @Validated DeviceVo deviceVo){
        return deviceService.deleteDevice(deviceVo.getSn());
    }

    @PutMapping("/")
    public R updateDeviceInfo(@RequestBody @Validated(value = UpdateGroup.class) DeviceVo deviceVo){
        return deviceService.updateDeviceInfo(deviceVo);
    }

    @GetMapping("/")
    public R<List<DeviceCustomerVo>> queryDevice(@RequestBody @Validated(value = QueryGroup.class) DeviceVo deviceVo){
        return deviceService.queryPage(deviceVo);
    }


    /**
     * 心跳包接口，向kafka消息队列发送消息
     */
    @GetMapping("/heart")
    public void sendHeartByKafka(@RequestParam(required = true) String sn, @RequestParam(required = true) int online){
        kafkaProducer.send(sn,online);
    }

}
