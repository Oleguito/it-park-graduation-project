package ru.itpark.notificationservice.infrastructure.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DefaultKafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    protected Map<String, Object> getConsumerProps() {
        log.warn("Бутстрапик: {}", bootstrap);
        
        Map<String, Object> props = new HashMap<>();
        
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return props;
    }

    protected Map<String, Object> getConsumerPropsNotificationMessage() {
        log.info("Бутстрап NotificationMessage: {}", bootstrap);
        
        Map<String, Object> props = new HashMap<>();
        
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return props;
    }

}
