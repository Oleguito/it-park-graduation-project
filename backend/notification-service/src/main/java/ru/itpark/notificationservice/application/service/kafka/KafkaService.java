package ru.itpark.notificationservice.application.service.kafka;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaService {

    private final String topic = "notification-topic";
    private final String groupId = "notification-group";

    @KafkaListener(topics = topic, groupId = groupId)
    public void receiveNotification(@Payload ConsumerRecord<String, String> msg) {
        System.out.println(String.format("Полученное сообщение: %s", msg));
    }

}
