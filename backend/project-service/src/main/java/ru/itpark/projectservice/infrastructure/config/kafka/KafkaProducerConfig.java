package ru.itpark.projectservice.infrastructure.config.kafka;

import ru.itpark.sharedlib.InvitationMessage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

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
    public KafkaTemplate<String, InvitationMessage> kafkaTemplateNM() {
        return new KafkaTemplate<>(producerFactoryNM());
    }

    public DefaultKafkaProducerFactory<String, InvitationMessage> producerFactoryNM() {
        return new DefaultKafkaProducerFactory<>(getProducerProps());
    }
}
