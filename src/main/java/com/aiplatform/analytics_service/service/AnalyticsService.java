package com.aiplatform.analytics_service.service;

import com.aiplatform.analytics_service.client.TrackingClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AnalyticsService {

    private final TrackingClient trackingClient;

    public AnalyticsService(TrackingClient trackingClient) {
        this.trackingClient = trackingClient;
    }

    public List<Map<String, Object>> getDailyCalories(String authHeader) {

        List<Map<String, Object>> entries = trackingClient.fetchDietTracking(authHeader);

        Map<LocalDate, Integer> caloriesByDate = entries.stream()
                .collect(Collectors.groupingBy(
                        e -> {
                            String timestamp = (String) e.get("timestamp");
                            return LocalDateTime.parse(timestamp).toLocalDate();
                        },
                        Collectors.summingInt(
                                e -> (Integer) e.get("calories"))));

        return caloriesByDate.entrySet().stream()
                .map(e -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("date", e.getKey().toString());
                    map.put("calories", e.getValue());
                    return map;
                })
                .sorted(Comparator.comparing(m -> (String) m.get("date")))
                .collect(Collectors.toList());
    }
}