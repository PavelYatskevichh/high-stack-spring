package com.yatskevich.hs.spring.moderation.dto;

import com.yatskevich.hs.spring.moderation.entity.ReviewStatus;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDataDto {
    @NotNull(message = "Content ID must not be null.")
    private UUID contentId;

    private ReviewStatus status;

    private String message;

    private List<QualityMetricIdWithScoreDto> qualityMetricIdWithScoreDtos;
}
