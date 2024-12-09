package com.yatskevich.hs.spring.content_creation.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevisionDto {
    @NotNull(message = "Content ID must not be null.")
    private UUID contentId;
}
