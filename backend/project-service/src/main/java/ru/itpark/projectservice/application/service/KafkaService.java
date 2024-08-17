package ru.itpark.projectservice.application.service;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.itpark.projectservice.infrastructure.kafka.InvitationMessage;

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
    private final Gson gson = new Gson();

    public void sendNotificationMessage(String topic, InvitationMessage message) {
        int hash = Objects.hash(message);
        messageConstructor.processNotification(message);
        kafkaTemplate.send(notificationTopic,
                String.valueOf(hash),
                gson.toJson(message));
    }

    public void sendMessage(String topic, String message) {
        int hash = Objects.hash(message);
        kafkaTemplate.send(notificationTopic,
                String.valueOf(hash),
                "Message was: " + message);
    }

}

