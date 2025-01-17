package com.yatskevich.hs.spring.moderation.repository;

import com.yatskevich.hs.spring.moderation.entity.QualityMetric;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualityMetricRepository extends JpaRepository<QualityMetric, UUID> {
}
