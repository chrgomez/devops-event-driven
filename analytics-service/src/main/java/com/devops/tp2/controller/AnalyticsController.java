package com.devops.tp2.controller;

import com.devops.tp2.service.StatsService;
import java.util.Collections;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {
    
    private final StatsService statsService;

    public AnalyticsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats")
    public Map<String, Integer> getStats() {
        return Collections.singletonMap("total_tareas_procesadas", statsService.getCount());
    }
}
