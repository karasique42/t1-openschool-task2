package ru.nikolotov.common.dto;

import lombok.Data;

@Data
public class MemoryMetricsDto {

    private Double jvmMemoryUsedHeap;

    private Double jvmMemoryUsedNonHeap;

    private Double jvmMemoryMaxHeap;

    private Double jvmMemoryMaxNonHeap;
}
