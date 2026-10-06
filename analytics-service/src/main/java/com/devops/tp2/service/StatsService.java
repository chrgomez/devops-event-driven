package com.devops.tp2.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    
    private final MeterRegistry meterRegistry;
    private Counter taskCounter;

    public StatsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        this.taskCounter = Counter.builder("tareas_procesadas_total")
                .description("Total de tareas procesadas")
                .register(meterRegistry);
    }

    public void increment() {
        if (taskCounter != null) {
            taskCounter.increment();
        }
    }

    public int getCount() {
        return taskCounter != null ? (int) taskCounter.count() : 0;
    }
    
}