package ru.nikolotov.metricsconsumer.dto;

import lombok.Data;

@Data
public class DbMetricsDto {

    private Double hikariConnectionsIdle;

    private Double hikariConnectionsActive;
}
