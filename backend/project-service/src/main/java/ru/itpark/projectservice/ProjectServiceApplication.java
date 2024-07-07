package ru.itpark.projectservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
public class ProjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceApplication.class, args);
	}

	@KafkaListener(topics = {"test"}, groupId = "my-group", containerFactory = "kafkaListenerContainerFactory")
	public void listen(@Payload ConsumerRecord<String, String> message) {
		System.out.println("Received: " + message.value());
	}
}
