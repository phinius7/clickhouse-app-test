package ru.senchenko.event.generator.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Import(EventProducerConfig.class)
public class EventConfig {

    @Value("${spring.kafka.producer.bootstrap.servers}")
    private String kafkaBootstrapServers;


    @Bean
    NewTopic commonTopic() {
        return new NewTopic("common-topic", 4, (short) 1);
    }


    @Bean
    KafkaAdmin kafkaAdmin() {
        final Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        return new KafkaAdmin(configs);
    }

}
