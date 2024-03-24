package ru.nikolotov.common.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MetricsDto {

    private UUID id;
    private LocalDateTime timestamp;
    private CpuMetricsDto cpuMetrics;
    private MemoryMetricsDto memoryMetrics;
}
