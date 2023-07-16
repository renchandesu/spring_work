package com.hillstone.kafka;

import com.alibaba.fastjson.JSONObject;
import com.hillstone.constant.Constants;
import com.hillstone.dao.RedisClient;
import com.hillstone.entity.vo.HeartBeat;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    private RedisClient redisClient;

    @KafkaListener(topics = Constants.TOPIC_NAME, groupId = Constants.GROUP_ID)
    public void handleHeartBeat(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            String msg;
            HeartBeat obj;
            try {
                msg = (String) message.get();
                obj = JSONObject.parseObject(msg,HeartBeat.class);
                redisClient.setStatus(obj.getSn(),obj.getOnline());
            } catch (Exception e) {
                log.error("心跳包格式不正确，忽略");
                ack.acknowledge();
                return;
            }
            log.info("心跳包已接收 设备sn[{}],在线状态:[{}]", obj.getSn(), obj.getOnline());
            ack.acknowledge();
        }
    }

}