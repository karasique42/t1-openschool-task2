package ru.nikolotov.metricsconsumer.mapper;

import org.mapstruct.Mapper;
import ru.nikolotov.common.dto.MetricsDto;
import ru.nikolotov.metricsconsumer.repository.entity.MetricsEntity;

import java.util.List;

@Mapper
public interface MetricsMapper {

    MetricsEntity dtoToEntity(MetricsDto dto);

    MetricsDto entityToDto(MetricsEntity entity);

    List<MetricsDto> entitiesToDtos(List<MetricsEntity> entity);
}
