package com.aiplatform.analytics_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;

@Component
public class TrackingClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${tracking.service.url}")
    private String trackingServiceUrl;

    public List<Map<String, Object>> fetchDietTracking(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                trackingServiceUrl + "/tracking/diet",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                });

        return response.getBody();
    }
}