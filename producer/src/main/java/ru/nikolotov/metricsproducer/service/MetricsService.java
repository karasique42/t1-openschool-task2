package ru.nikolotov.metricsproducer.service;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nikolotov.metricsproducer.kafka.KafkaSender;
import ru.nikolotov.metricsproducer.service.model.CpuMetrics;
import ru.nikolotov.metricsproducer.service.model.MemoryMetrics;
import ru.nikolotov.metricsproducer.service.model.MetricModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetricsService {

    private final KafkaSender kafkaSender;
    private final MeterRegistry meterRegistry;

    public void sendMetrics() {
        log.info("Sending metrics...");
        kafkaSender.sendMetrics(collectMetrics());
        log.info("Metrics have been sent");
    }

    private MetricModel collectMetrics() {
        UUID id = UUID.randomUUID();
        LocalDateTime timestamp = LocalDateTime.now();
        return new MetricModel(id, timestamp, collectCpuMetrics(), collectMemoryMetrics());
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
