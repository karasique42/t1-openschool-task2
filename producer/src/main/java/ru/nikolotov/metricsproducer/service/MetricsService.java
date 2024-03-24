package ru.nikolotov.metricsproducer.service;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.nikolotov.metricsproducer.kafka.KafkaSender;
import ru.nikolotov.metricsproducer.service.model.CpuMetrics;
import ru.nikolotov.metricsproducer.service.model.DbMetrics;
import ru.nikolotov.metricsproducer.service.model.MemoryMetrics;
import ru.nikolotov.metricsproducer.service.model.MetricModel;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsService {

    private final KafkaSender kafkaSender;
    private final MeterRegistry meterRegistry;

//    @Scheduled(fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    public void sendMetrics() {
        log.info("Sending metrics...");
        kafkaSender.sendMetrics(collectMetrics());
        log.info("Metrics have been sent");
    }

    private MetricModel collectMetrics() {
        UUID id = UUID.randomUUID();
        Long timestamp = System.currentTimeMillis();
        return new MetricModel(id, timestamp, collectCpuMetrics(), collectMemoryMetrics(), collectDbMetrics());
    }

    private DbMetrics collectDbMetrics() {
        double hikariActiveConnections = meterRegistry.get("hikaricp.connections.active")
                .tag("pool", "HikariPool-1").gauge().value();
        double hikariIdleConnections = meterRegistry.get("hikaricp.connections.idle")
                .tag("pool", "HikariPool-1").gauge().value();

        return new DbMetrics(hikariIdleConnections, hikariActiveConnections);
    }

    private MemoryMetrics collectMemoryMetrics() {
        double heapMemoryUsage = meterRegistry.get("jvm.memory.used").tag("area", "heap").gauge().value();
        double nonHeapMemoryUsage = meterRegistry.get("jvm.memory.used").tag("area", "nonheap").gauge().value();
        double heapMemoryMax = meterRegistry.get("jvm.memory.max").tag("area", "heap").gauge().value();
        double nonHeapMemoryMax = meterRegistry.get("jvm.memory.max").tag("area", "nonheap").gauge().value();

        return new MemoryMetrics(heapMemoryUsage, nonHeapMemoryUsage, heapMemoryMax, nonHeapMemoryMax);
    }

    private CpuMetrics collectCpuMetrics() {
        double cpuUsed = meterRegistry.get("process.cpu.usage").gauge().value();
        double threadCount = meterRegistry.get("jvm.threads.live").gauge().value();

        return new CpuMetrics(cpuUsed, threadCount);
    }
}
