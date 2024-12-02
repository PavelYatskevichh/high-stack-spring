package com.yatskevich.hs.spring.moderation.repository;

import com.yatskevich.hs.spring.moderation.entity.Review;
import com.yatskevich.hs.spring.moderation.entity.ReviewQualityMetric;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void findById_ExistingId_ReturnEntity() {
        Review review = reviewRepository.findById(UUID.fromString("88888888-8888-8888-8888-888888888881"))
            .orElseThrow();

        List<ReviewQualityMetric> reviewQualityMetrics = review.getReviewQualityMetrics();
        List<UUID> resultQualityMetricUuids = reviewQualityMetrics.stream()
            .map(e -> e.getQualityMetric().getId())
            .sorted()
            .toList();
        System.out.println(resultQualityMetricUuids);
        List<UUID> expected = List.of(
            UUID.fromString("99999999-9999-9999-9999-999999999991"),
            UUID.fromString("99999999-9999-9999-9999-999999999992")
        );

        assertThat(resultQualityMetricUuids).isNotEmpty();
        assertEquals(expected, resultQualityMetricUuids);
    }
}
