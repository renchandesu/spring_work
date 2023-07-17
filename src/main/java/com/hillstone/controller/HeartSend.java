package com.hillstone.controller;

import com.hillstone.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartSend {


    @Autowired
    KafkaProducer kafkaProducer;

    /**
     * 心跳包接口，向kafka消息队列发送消息 也可以直接通过kafka内的生产者命令进入终端直接发送
     */
    @GetMapping("/heart")
    public void sendHeartByKafka(@RequestParam(required = true) String sn, @RequestParam(required = true) int online){
        kafkaProducer.send(sn,online);
    }

}
