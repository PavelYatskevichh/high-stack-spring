package com.yatskevich.hs.spring.moderation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "moderation", name = "review_quality_metrics")
public class ReviewQualityMetric {

    @EmbeddedId
    private ReviewQualityMetricId id;

    @ManyToOne
    @MapsId("reviewId")
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne
    @MapsId("qualityMetricId")
    @JoinColumn(name = "quality_metric_id", nullable = false)
    private QualityMetric qualityMetric;

    @Column(name = "score")
    private Integer score;
}
