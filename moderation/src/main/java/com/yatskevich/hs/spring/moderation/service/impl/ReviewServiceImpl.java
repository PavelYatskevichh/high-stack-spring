package com.yatskevich.hs.spring.moderation.service.impl;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.moderation.dto.QualityMetricIdWithScoreDto;
import com.yatskevich.hs.spring.moderation.dto.ReviewDataDto;
import com.yatskevich.hs.spring.moderation.dto.ReviewDto;
import com.yatskevich.hs.spring.moderation.entity.QualityMetric;
import com.yatskevich.hs.spring.moderation.entity.Review;
import com.yatskevich.hs.spring.moderation.entity.ReviewQualityMetric;
import com.yatskevich.hs.spring.moderation.entity.ReviewQualityMetricId;
import com.yatskevich.hs.spring.moderation.entity.ReviewStatus;
import com.yatskevich.hs.spring.moderation.mapper.ReviewMapper;
import com.yatskevich.hs.spring.moderation.repository.ReviewRepository;
import com.yatskevich.hs.spring.moderation.service.KafkaService;
import com.yatskevich.hs.spring.moderation.service.QualityMetricService;
import com.yatskevich.hs.spring.moderation.service.ReviewService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    public static final String CONTENT_STATUS_DRAFT = "DRAFT";
    private final ReviewRepository reviewRepository;
    private final QualityMetricService qualityMetricService;
    private final ReviewMapper reviewMapper;
    private final KafkaService kafkaService;

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDto> getAll() {
        log.debug("Searching for all the reviews in the database.");
        return reviewMapper.toDtoList(reviewRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewDto getById(UUID reviewId) {
        return reviewMapper.toDto(findByIdOrElseThrow(reviewId));
    }

    private Review findByIdOrElseThrow(UUID reviewId) {
        log.debug("Searching for the review {} in the database.", reviewId);
        return reviewRepository.findById(reviewId).orElseThrow(() -> {
            log.error("The review {} is not found in the database.", reviewId);
            //FIXME create exception
            return new RuntimeException("The review %s is not found in the database."
                .formatted(reviewId));
        });
    }

    @Override
    public void create(ReviewDataDto reviewDataDto, UUID reviewerId) {
        Map<UUID, Integer> qualityMetricIdScoreMap = reviewDataDto.getQualityMetricIdWithScoreDtos().stream()
            .collect(Collectors.toMap(QualityMetricIdWithScoreDto::getId, QualityMetricIdWithScoreDto::getScore));

        Review review = Review.builder()
            .contentId(reviewDataDto.getContentId())
            .reviewerId(reviewerId)
            .status(reviewDataDto.getStatus())
            .message(reviewDataDto.getMessage())
            .build();

        List<QualityMetric> qualityMetrics = qualityMetricService.findAllById(qualityMetricIdScoreMap.keySet());

        List<ReviewQualityMetric> reviewQualityMetrics = qualityMetrics.stream()
            .map(qualityMetric ->
                new ReviewQualityMetric(
                    new ReviewQualityMetricId(qualityMetric.getId(), review.getId()),
                    review,
                    qualityMetric,
                    qualityMetricIdScoreMap.get(qualityMetric.getId())
                )
            )
            .collect(Collectors.toList());

        review.setReviewQualityMetrics(reviewQualityMetrics);

        log.debug("Saving new review for the content {} by reviewer {} to the database.",
            reviewDataDto.getContentId(), reviewerId);
        reviewRepository.save(review);

        kafkaService.sendMessageUpdateContentStatus(newContentStatusDto(reviewDataDto));
    }

    private ContentStatusDto newContentStatusDto(ReviewDataDto reviewDataDto) {
        ContentStatusDto contentStatusDto = new ContentStatusDto();
        contentStatusDto.setId(reviewDataDto.getContentId());
        contentStatusDto.setStatus(switch (reviewDataDto.getStatus()) {
            case APPROVED -> ReviewStatus.APPROVED.name();
            case REJECTED -> ReviewStatus.REJECTED.name();
            case NEEDS_REVISION -> CONTENT_STATUS_DRAFT;
            default -> throw new RuntimeException("There is no such Content Status: %s."
                .formatted(reviewDataDto.getStatus()));
        });
        return contentStatusDto;
    }
}
