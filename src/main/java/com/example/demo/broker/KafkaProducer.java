package com.example.demo.broker;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {
    private KafkaTemplate<String, String> kafkaTemplate;

    public void produce(String topic, String message) {
        System.out.println("Sent message: " + message);
        kafkaTemplate.send(topic, message);
    }
}
