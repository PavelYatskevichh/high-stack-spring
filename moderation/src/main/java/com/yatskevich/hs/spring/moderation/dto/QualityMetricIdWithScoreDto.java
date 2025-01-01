package com.yatskevich.hs.spring.moderation.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QualityMetricIdWithScoreDto {
    @NotNull(message = "Quality metric ID must not be null.")
    private UUID id;
    @NotNull(message = "Score must not be null.")
    private Integer score;
}
