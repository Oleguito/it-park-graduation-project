package ru.itpark.projectservice.application.service;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class KafkaService {

    @Value("${notification.topic}")
    private String notificationTopic;
    @Value("${notification.group}")
    private String notificationGroup;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        int hash = Objects.hash(message);
        kafkaTemplate.send(notificationTopic, String.valueOf(hash), "Привет! Это тестовое сообщение");
    }

}

