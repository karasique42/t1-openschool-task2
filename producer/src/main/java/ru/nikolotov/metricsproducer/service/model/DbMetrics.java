package ru.nikolotov.metricsproducer.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DbMetrics {

    private Double hikariConnectionsIdle;

    private Double hikariConnectionsActive;
}
