package com.yatskevich.hs.spring.moderation.service;

import com.yatskevich.hs.spring.moderation.entity.QualityMetric;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface QualityMetricService {
    QualityMetric findByIdOrElseThrow(UUID qualityMetricId);

    List<QualityMetric> findAllById(Iterable<UUID> qualityMetricIds);
}
