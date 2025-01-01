package com.yatskevich.hs.spring.moderation.mapper;

import com.yatskevich.hs.spring.moderation.dto.QualityMetricWithScoreDto;
import com.yatskevich.hs.spring.moderation.dto.ReviewDto;
import com.yatskevich.hs.spring.moderation.entity.Review;
import com.yatskevich.hs.spring.moderation.entity.ReviewQualityMetric;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "qualityMetricWithScoreDtos",
        expression = "java(setQualityMetricWithScoreDtos(review.getReviewQualityMetrics()))")
    ReviewDto toDto(Review review);

    List<ReviewDto> toDtoList(List<Review> reviews);

    default List<QualityMetricWithScoreDto> setQualityMetricWithScoreDtos(List<ReviewQualityMetric> reviewQualityMetrics
    ) {
        return reviewQualityMetrics.stream()
            .map(reviewQualityMetric ->
                new QualityMetricWithScoreDto(
                    reviewQualityMetric.getQualityMetric().getId(),
                    reviewQualityMetric.getQualityMetric().getName(),
                    reviewQualityMetric.getQualityMetric().getDescription(),
                    reviewQualityMetric.getScore()
                )
            )
            .toList();
    }
}
