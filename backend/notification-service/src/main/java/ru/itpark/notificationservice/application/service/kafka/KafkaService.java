package ru.itpark.notificationservice.application.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import ru.itpark.sharedlib.NotificationMessage;

@Service
@EnableKafka
public class KafkaService {

    private final String topic = "notification-topic";
    private final String groupId = "notification-group";

    @KafkaListener(topics = topic, groupId = groupId)
    public void receiveNotification(@Payload ConsumerRecord<String, String> msg) {
        System.out.println(String.format("Полученное сообщение: %s", msg));
    }

    @KafkaListener(topics = topic, groupId = groupId)
    public void receiveNotificationMessage(@Payload ConsumerRecord<String, NotificationMessage> msg) {
        System.out.println(String.format("Полученное сообщение (объект): %s", msg));
    }

}
