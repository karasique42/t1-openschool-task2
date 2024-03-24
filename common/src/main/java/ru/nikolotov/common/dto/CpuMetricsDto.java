package ru.nikolotov.common.dto;

import lombok.Data;

@Data
public class CpuMetricsDto {

    private Double cpuUsage;
    private Double aliveThreads;
}
