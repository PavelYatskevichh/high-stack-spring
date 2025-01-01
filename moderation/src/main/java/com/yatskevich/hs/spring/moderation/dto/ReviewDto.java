package com.yatskevich.hs.spring.moderation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yatskevich.hs.spring.moderation.entity.ReviewStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private UUID id;
    private UUID contentId;
    private UUID reviewerId;
    private ReviewStatus status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime reviewedAt;
    private List<QualityMetricWithScoreDto> qualityMetricWithScoreDtos;
}
