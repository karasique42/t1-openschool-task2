package ru.nikolotov.metricsproducer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class MetricModel {

    private UUID id;
    private Long timestamp;
    private CpuMetrics cpuMetrics;
    private MemoryMetrics memoryMetrics;
    private DbMetrics dbMetrics;
}
