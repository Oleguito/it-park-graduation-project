package ru.itpark.projectservice.application.service;

import lombok.*;
import ru.itpark.sharedlib.InvitationMessage;
import ru.itpark.sharedlib.NotificationMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class KafkaService {

    @Value("${spring.kafka.notification.topic}")
    private String notificationTopic;
    @Value("${spring.kafka.notification.group}")
    private String notificationGroup;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MessageConstructor messageConstructor;
    private final KafkaTemplate<String, InvitationMessage> kafkaTemplateNM;

    public void sendNotificationMessage(String topic, InvitationMessage message) {
        int hash = Objects.hash(message);
        messageConstructor.processNotification(message);
        kafkaTemplate.send(notificationTopic, 
            String.valueOf(hash), 
            "Message was: " + message.toString());
    }

    public void sendMessage(String topic, String message) {
        int hash = Objects.hash(message);
        kafkaTemplate.send(notificationTopic, 
            String.valueOf(hash), 
            "Message was: " + message);
    }

}

