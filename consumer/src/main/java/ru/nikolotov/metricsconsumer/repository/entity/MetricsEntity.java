package ru.nikolotov.metricsconsumer.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.nikolotov.common.dto.CpuMetricsDto;
import ru.nikolotov.common.dto.MemoryMetricsDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "logs")
@Getter
@Setter
public class MetricsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "time")
    private LocalDateTime timestamp;

    @Column(name = "cpu_metrics")
    @JdbcTypeCode(SqlTypes.JSON)
    private CpuMetricsDto cpuMetrics;

    @Column(name = "memory_metrics")
    @JdbcTypeCode(SqlTypes.JSON)
    private MemoryMetricsDto memoryMetrics;
}
