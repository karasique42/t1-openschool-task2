package ru.nikolotov.metricsproducer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CpuMetrics {

    private Double cpuUsage;
    private Double aliveThreads;
}
