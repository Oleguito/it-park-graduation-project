package ru.itpark.notificationservice.application.service.kafka;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import ru.itpark.notificationservice.application.service.notification.EmailSender;
import ru.itpark.notificationservice.infrastructure.kafka.InvitationMessage;

@Service
@EnableKafka
@RequiredArgsConstructor
public class KafkaService {

    private final String topic = "notification-topic";
    private final String groupId = "notification-group";
    private final EmailSender emailSender;
    private final Gson gson = new Gson();

//    @KafkaListener(topics = topic, groupId = groupId)
//    public void receiveNotification(@Payload ConsumerRecord<String, String> msg) {
//        System.out.println(String.format("Полученное сообщение: %s", msg));
//    }

    @KafkaListener(topics = topic, containerFactory = "kafkaListenerContainerFactory", groupId = groupId)
    public void receiveNotificationMessage(@Payload ConsumerRecord<String, String> kafkaMessage) {

        InvitationMessage msg = gson.fromJson(kafkaMessage.value(), InvitationMessage.class);

        System.out.println(String.format("Полученное сообщение (объект): %s", msg));
        emailSender.sendEmail(msg);
    }

}
