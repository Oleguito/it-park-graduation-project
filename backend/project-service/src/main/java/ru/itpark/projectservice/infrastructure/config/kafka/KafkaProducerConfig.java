package ru.itpark.projectservice.infrastructure.config.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import ru.itpark.projectservice.infrastructure.kafka.InvitationMessage;

@Configuration
public class KafkaProducerConfig extends DefaultKafkaConfig {

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    public DefaultKafkaProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(getProducerProps());
    }

    @Bean
    public KafkaTemplate<String, InvitationMessage> kafkaTemplateNotificationMessage() {
        return new KafkaTemplate<>(producerFactoryNotificationMessage());
    }

    public DefaultKafkaProducerFactory<String, InvitationMessage> producerFactoryNotificationMessage() {
        return new DefaultKafkaProducerFactory<>(getProducerPropsNotificationMessage());
    }
}
