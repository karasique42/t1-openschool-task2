package ru.nikolotov.metricsconsumer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nikolotov.common.dto.MetricsDto;
import ru.nikolotov.metricsconsumer.exception.NotFoundException;
import ru.nikolotov.metricsconsumer.mapper.MetricsMapper;
import ru.nikolotov.metricsconsumer.repository.MetricsRepository;
import ru.nikolotov.metricsconsumer.repository.entity.MetricsEntity;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/metrics")
@Tag(name="Метрики", description="Методы сбора метрик")
public class MetricsController {

    private final MetricsMapper metricsMapper;
    private final MetricsRepository metricsRepository;

    @GetMapping
    @Operation(summary = "Запрос всех метрик")
    public List<MetricsDto> getMetrics() {
        List<MetricsEntity> metricsEntities = metricsRepository.findAll();
        return metricsMapper.entitiesToDtos(metricsEntities);
    }

    @GetMapping(path = "/{uuid}")
    @Operation(summary = "Запрос метрики по UUID")
    public MetricsDto getMetricsById(@PathVariable UUID uuid) {
        MetricsEntity metricsEntity = metricsRepository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException("Metric record with uuid " + uuid
                        + " was not found"));
        return metricsMapper.entityToDto(metricsEntity);
    }
}
