package com.aiplatform.analytics_service.service;

import com.aiplatform.analytics_service.client.TrackingClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final TrackingClient trackingClient;

    public AnalyticsService(TrackingClient trackingClient) {
        this.trackingClient = trackingClient;
    }

    public Map<String, Object> getSummary(String authHeader) {

        List<Map<String, Object>> entries = trackingClient.fetchUserTracking(authHeader);

        Map<String, Long> countByDomain = entries.stream()
                .collect(Collectors.groupingBy(
                        e -> (String) e.get("domain"),
                        Collectors.counting()));

        return Map.of(
                "totalEntries", entries.size(),
                "byDomain", countByDomain);
    }
}