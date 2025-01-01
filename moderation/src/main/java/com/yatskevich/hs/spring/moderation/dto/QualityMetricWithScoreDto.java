package com.yatskevich.hs.spring.moderation.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QualityMetricWithScoreDto {
    private UUID id;
    private String name;
    private String description;
    private Integer score;
}
