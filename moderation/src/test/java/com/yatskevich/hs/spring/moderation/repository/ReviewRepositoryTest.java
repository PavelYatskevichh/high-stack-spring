package com.yatskevich.hs.spring.moderation.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RequiredArgsConstructor
@Transactional
public class ReviewRepositoryTest {

    private ReviewRepository reviewRepository;

//    @Test
//    void findById_ExistingId_ReturnEntity() {
//        Review review = reviewRepository.findById(UUID.fromString("88888888-8888-8888-8888-888888888881"))
//            .orElseThrow();
//
//        List<ReviewQualityMetric> reviewQualityMetrics = review.getReviewQualityMetrics();
//        List<UUID> resultQualityMetricUuids = reviewQualityMetrics.stream()
//            .map(e -> e.getQualityMetric().getId())
//            .sorted()
//            .toList();
//        System.out.println(resultQualityMetricUuids);
//        List<UUID> expected = List.of(
//            UUID.fromString("99999999-9999-9999-9999-999999999991"),
//            UUID.fromString("99999999-9999-9999-9999-999999999991")
//        );
//
//        assertThat(resultQualityMetricUuids).isNotEmpty();
//        assertEquals(expected, resultQualityMetricUuids);
//    }
}
