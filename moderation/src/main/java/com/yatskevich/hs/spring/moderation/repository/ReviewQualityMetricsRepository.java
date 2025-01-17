package com.yatskevich.hs.spring.moderation.repository;

import com.yatskevich.hs.spring.moderation.entity.ReviewQualityMetric;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewQualityMetricsRepository extends JpaRepository<ReviewQualityMetric, UUID> {
}
