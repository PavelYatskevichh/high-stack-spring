package com.yatskevich.hs.spring.moderation.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yatskevich.hs.spring.moderation.AbstractTestcontainersTests;
import com.yatskevich.hs.spring.moderation.entity.Review;
import com.yatskevich.hs.spring.moderation.entity.ReviewQualityMetric;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles({"test"})
class ReviewRepositoryTest extends AbstractTestcontainersTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Sql({"/scripts/insert_data_for_review.sql"})
    void findById_ExistingId_ReturnEntity() {
        Review review = reviewRepository.findById(UUID.fromString("88888888-dddd-8888-8888-888888888881"))
            .orElseThrow();

        List<ReviewQualityMetric> reviewQualityMetrics = review.getReviewQualityMetrics();
        List<UUID> resultQualityMetricUuids = reviewQualityMetrics.stream()
            .map(e -> e.getQualityMetric().getId())
            .sorted()
            .toList();
        System.out.println(resultQualityMetricUuids);
        List<UUID> expected = List.of(
            UUID.fromString("99999999-dddd-9999-9999-999999999991"),
            UUID.fromString("99999999-dddd-9999-9999-999999999992")
        );

        assertThat(resultQualityMetricUuids).isNotEmpty();
        assertEquals(expected, resultQualityMetricUuids);
    }
}
