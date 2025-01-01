package com.yatskevich.hs.spring.moderation.repository;

import com.yatskevich.hs.spring.moderation.entity.ReviewQualityMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewQualityMetricsRepository extends JpaRepository<ReviewQualityMetric, UUID> {
}
