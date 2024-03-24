package ru.nikolotov.metricsconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikolotov.metricsconsumer.repository.entity.MetricsEntity;

import java.util.UUID;

@Repository
public interface MetricsRepository extends JpaRepository<MetricsEntity, UUID> {
}
