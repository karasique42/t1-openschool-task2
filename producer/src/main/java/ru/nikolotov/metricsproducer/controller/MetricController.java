package ru.nikolotov.metricsproducer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikolotov.metricsproducer.service.MetricsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/metrics")
public class MetricController {

    private final MetricsService metricsService;

    @PostMapping
    public void collectMetrics() {
        metricsService.sendMetrics();
    }
}
