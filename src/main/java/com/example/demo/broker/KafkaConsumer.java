package com.example.demo.broker;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "testtopic", groupId = "testgroup")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
