package com.testcontainer.demo.service;

import com.testcontainer.demo.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> template;
    public void sendMessageToTopic(Customer customer) {
        CompletableFuture<SendResult<String, Object>> future = template.send("DEMO-TOPIC", customer);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent customer=[" + customer.getEmail() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                log.error("Unable to send message=[" + customer + "] due to : " + ex.getMessage());
            }
        });

    }
}
