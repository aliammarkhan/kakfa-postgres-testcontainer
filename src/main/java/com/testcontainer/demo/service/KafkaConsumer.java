package com.testcontainer.demo.service;

import com.testcontainer.demo.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    @Autowired
    private final CustomerService customerService;

    @KafkaListener(topics = "DEMO-TOPIC", groupId = "jt-group")
    public void consumeEvents(Customer customer) {
        log.info("Received Message from customer: {}", customer.getName());
        customerService.saveCustomer(customer);
    }
}