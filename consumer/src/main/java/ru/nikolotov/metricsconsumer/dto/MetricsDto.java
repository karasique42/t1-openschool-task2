package ru.nikolotov.metricsconsumer.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MetricsDto {

    private UUID id;
    private Long timestamp;
    private CpuMetricsDto cpuMetrics;
    private MemoryMetricsDto memoryMetrics;
    private DbMetricsDto dbMetrics;
}
