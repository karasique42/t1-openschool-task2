package ru.nikolotov.metricsproducer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MetricModel {

    private UUID id;
    private LocalDateTime timestamp;
    private CpuMetrics cpuMetrics;
    private MemoryMetrics memoryMetrics;
}
