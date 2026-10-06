package com.devops.tp2.analytics;

import com.devops.tp2.service.StatsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsConsumer {

    private final StatsService statsService;

    public AnalyticsConsumer(StatsService statsService) {
        this.statsService = statsService;
    }

    @KafkaListener(topics = "tareas-topic", groupId = "analytics-group")
    public void listen(String mensaje) {
        statsService.increment(); // Esto disparará la métrica de Prometheus
    }

}
