package com.yatskevich.hs.spring.moderation.service.impl;

import com.yatskevich.hs.spring.moderation.entity.QualityMetric;
import com.yatskevich.hs.spring.moderation.entity.Review;
import com.yatskevich.hs.spring.moderation.mapper.ReviewMapper;
import com.yatskevich.hs.spring.moderation.repository.QualityMetricRepository;
import com.yatskevich.hs.spring.moderation.service.QualityMetricService;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class QualityMetricServiceImpl implements QualityMetricService {

    private final QualityMetricRepository qualityMetricRepository;

    @Override
    public QualityMetric findByIdOrElseThrow(UUID qualityMetricId) {
        log.debug("Searching for the quality metric {} in the database.", qualityMetricId);
        return qualityMetricRepository.findById(qualityMetricId).orElseThrow(() -> {
            log.error("The quality metric {} is not found in the database.", qualityMetricId);
            //FIXME create exception
            return new RuntimeException("The quality metric %s is not found in the database."
                .formatted(qualityMetricId));
        });
    }

    @Override
    public List<QualityMetric> findAllById(Iterable<UUID> qualityMetricIds) {
        log.debug("Searching for all the quality metrics in the database.");
        return qualityMetricRepository.findAllById(qualityMetricIds);
    }
}
