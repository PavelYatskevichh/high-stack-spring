package com.yatskevich.hs.spring.moderation.repository;

import com.yatskevich.hs.spring.moderation.entity.QualityMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface QualityMetricRepository extends JpaRepository<QualityMetric, UUID> {
}
