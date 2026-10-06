package com.devops.tp2.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic tareasTopic() {
        return TopicBuilder.name("tareas-topic")
                .partitions(3)
                .replicas(1)
                .build();
    }
}