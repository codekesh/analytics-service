package com.aiplatform.analytics_service.controller;

import com.aiplatform.analytics_service.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public Map<String, Object> summary(
            @RequestHeader("Authorization") String authHeader) {

        return analyticsService.getSummary(authHeader);
    }
}
