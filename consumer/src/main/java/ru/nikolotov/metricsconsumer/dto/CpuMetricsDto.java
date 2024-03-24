package ru.nikolotov.metricsconsumer.dto;

import lombok.Data;

@Data
public class CpuMetricsDto {

    private Double cpuUsage;
    private Double aliveThreads;
}
