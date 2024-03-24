package ru.nikolotov.metricsproducer.mapper;

import org.mapstruct.Mapper;
import ru.nikolotov.common.dto.MetricsDto;
import ru.nikolotov.metricsproducer.service.model.MetricModel;

@Mapper
public interface MetricsMapper {

    MetricsDto modelToDto(MetricModel model);
}
