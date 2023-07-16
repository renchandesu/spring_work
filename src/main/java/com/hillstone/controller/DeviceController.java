package com.hillstone.controller;

import com.hillstone.entity.po.Device;
import com.hillstone.entity.vo.DeviceCustomerVo;
import com.hillstone.entity.vo.DeviceVo;
import com.hillstone.kafka.KafkaProducer;
import com.hillstone.service.DeviceService;
import com.hillstone.util.R;
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

    /**
     * 心跳包接口，向kafka消息队列发送消息
     */
    @GetMapping("/heart")
    public void sendHeartByKafka(@RequestParam(required = true) String sn, @RequestParam(required = true) int online){
        kafkaProducer.send(sn,online);
    }

    @PostMapping("/")
    public R addDevice(@RequestBody @Validated DeviceVo deviceVo){
        return deviceService.addDevice(deviceVo);
    }

    @DeleteMapping("/")
    public R deleteDevice(@RequestParam String sn){
        return deviceService.deleteDevice(sn);
    }

    @PutMapping("/")
    public R updateDeviceInfo(@RequestBody DeviceVo deviceVo){
        return deviceService.updateDeviceInfo(deviceVo);
    }

    @GetMapping("/")
    public R<List<DeviceCustomerVo>> queryDevice(@RequestBody DeviceVo deviceVo){
        return deviceService.queryPage(deviceVo);
    }

}
