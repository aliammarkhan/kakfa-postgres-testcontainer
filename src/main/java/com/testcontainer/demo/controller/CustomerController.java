package com.testcontainer.demo.controller;

import com.testcontainer.demo.model.Customer;
import com.testcontainer.demo.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
         kafkaProducer.sendMessageToTopic(customer);
         return ResponseEntity.ok().body("SUCCESS");
    }
}
