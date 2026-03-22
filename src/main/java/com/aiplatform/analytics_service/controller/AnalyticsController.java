package com.aiplatform.analytics_service.controller;

import com.aiplatform.analytics_service.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/diet/daily")
    public List<Map<String, Object>> getDailyCalories(
            @RequestHeader("Authorization") String authHeader) {

        return analyticsService.getDailyCalories(authHeader);
    }
}