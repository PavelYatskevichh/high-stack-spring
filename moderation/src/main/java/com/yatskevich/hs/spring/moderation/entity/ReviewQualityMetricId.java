package com.yatskevich.hs.spring.moderation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewQualityMetricId {

    @Column(name = "review_id")
    private UUID reviewId;

    @Column(name = "quality_metric_id")
    private UUID qualityMetricId;
}
