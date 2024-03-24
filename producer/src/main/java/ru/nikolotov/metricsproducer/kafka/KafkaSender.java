package ru.nikolotov.metricsproducer.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.nikolotov.common.dto.MetricsDto;
import ru.nikolotov.metricsproducer.mapper.MetricsMapper;
import ru.nikolotov.metricsproducer.service.model.MetricModel;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaSender {

    private final MetricsMapper metricsMapper;
    private final KafkaTemplate<String, MetricsDto> kafkaTemplate;
    private final String topic = "metrics-topic";

    public void sendMetrics(MetricModel model) {
        MetricsDto dto = metricsMapper.modelToDto(model);
        kafkaTemplate.send(topic, dto);
    }
}
