package ru.itpark.projectservice.infrastructure.config.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class Consumer {

    @KafkaListener(topics = {"test"}, groupId = "my-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload ConsumerRecord<String, String> message) {
        System.out.println("Received: " + message.value());
    }

}
