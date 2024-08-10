package ru.itpark.notificationservice.infrastructure.config.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import ru.itpark.sharedlib.NotificationMessage;

@Configuration
public class KafkaConsumer extends DefaultKafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConcurrency(1);
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(getConsumerProps());
    }

    @Bean
    public ConsumerFactory<String, NotificationMessage> consumerFactoryNM() {
        return new DefaultKafkaConsumerFactory<>(getConsumerPropsNM());
    }

}
