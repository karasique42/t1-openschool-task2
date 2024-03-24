package ru.nikolotov.metricsproducer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikolotov.metricsproducer.service.MetricsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/metrics")
@Tag(name="Метрики", description="Методы для запроса на сохранение метрик")
public class MetricController {

    private final MetricsService metricsService;

    @PostMapping
    @Operation(summary = "Сохранение метрик")
    public void collectMetrics() {
        metricsService.sendMetrics();
    }
}
