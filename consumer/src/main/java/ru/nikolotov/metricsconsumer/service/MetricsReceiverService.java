package ru.nikolotov.metricsconsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.nikolotov.metricsconsumer.dto.MetricsDto;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
@Slf4j
public class MetricsReceiverService {

    private final RestTemplate restTemplate;

    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void requestMetrics() {
        restTemplate.postForObject("http://localhost:8081/api/v1/metrics", null, Void.class);
        log.info("Metrics have been requested");
    }

    @KafkaListener(id = "1", topics = "metrics-topic")
    public void listen(MetricsDto metricsDto) {
        log.info("Received metrics: {}", metricsDto);
    }
}
