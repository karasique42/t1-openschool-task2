package ru.nikolotov.metricsconsumer.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.nikolotov.common.dto.MetricsDto;
import ru.nikolotov.metricsconsumer.mapper.MetricsMapper;
import ru.nikolotov.metricsconsumer.repository.MetricsRepository;
import ru.nikolotov.metricsconsumer.repository.entity.MetricsEntity;

@Slf4j
@Component
@RequiredArgsConstructor
public class MetricsListener {

    private final MetricsRepository metricsRepository;
    private final MetricsMapper metricsMapper;

    @KafkaListener(id = "1", topics = "metrics-topic")
    public void listen(MetricsDto metricsDto) {
        log.info("Received metrics: {}", metricsDto);
        MetricsEntity entity = metricsMapper.dtoToEntity(metricsDto);
        metricsRepository.save(entity);
        log.info("Metrics have been saved");
    }
}
