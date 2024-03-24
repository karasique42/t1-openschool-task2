package ru.nikolotov.metricsproducer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemoryMetrics {

    private Double jvmMemoryUsedHeap;

    private Double jvmMemoryUsedNonHeap;

    private Double jvmMemoryMaxHeap;

    private Double jvmMemoryMaxNonHeap;
}
