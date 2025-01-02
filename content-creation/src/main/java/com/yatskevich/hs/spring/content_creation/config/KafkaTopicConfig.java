package com.yatskevich.hs.spring.content_creation.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

//    @Value(value = "${kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//    @Value(value = "${kafka.topic}")
//    private String topic;
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        return new KafkaAdmin(configProps);
//    }
//
//    @Bean
//    public NewTopic topic1() {
//        return new NewTopic(topic, 1, (short) 1);
//    }
}
