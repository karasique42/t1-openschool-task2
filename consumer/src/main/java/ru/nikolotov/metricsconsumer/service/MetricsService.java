package ru.nikolotov.metricsconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MetricsService {

    private final RestTemplate restTemplate;
    private final String producerMetricsUrl;

    public MetricsService(RestTemplate restTemplate, @Value("${producer.url}") String producerUrl) {
        this.restTemplate = restTemplate;
        this.producerMetricsUrl = producerUrl + "/metrics";
    }

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void requestMetrics() {
        restTemplate.postForObject(producerMetricsUrl, null, Void.class);
        log.info("Metrics have been requested");
    }
}
