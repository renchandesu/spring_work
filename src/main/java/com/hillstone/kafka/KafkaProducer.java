package com.hillstone.kafka;

import com.alibaba.fastjson.JSONObject;
import com.hillstone.constant.Constants;
import com.hillstone.entity.vo.HeartBeat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    /**
     *  生产者向kafka的Topic Constants.TOPIC_NAME发送消息
     */
    @Async
    public void send(String sn, Integer status) {

        HeartBeat obj = new HeartBeat();
        obj.setOnline(status);
        obj.setSn(sn);

        String obj2String = JSONObject.toJSONString(obj);
        // 发送消息
        kafkaTemplate.send(Constants.TOPIC_NAME, obj2String).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onFailure(Throwable throwable) {
                // 发送失败的处理 输出失败日志
                log.error("topic[{}] 生产者 发送消息失败[{}]", Constants.TOPIC_NAME, throwable.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {

                // 成功的处理
                log.info("topic[{}] 生产者 发送消息成功[{}]", Constants.TOPIC_NAME, stringObjectSendResult.getProducerRecord().value());
            }
        });
    }

}
