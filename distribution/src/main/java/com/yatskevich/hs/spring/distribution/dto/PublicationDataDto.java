package com.yatskevich.hs.spring.distribution.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationDataDto {
    @NotBlank(message = "Content ID must not be blank on null.")
    private UUID contentId;
    @NotBlank(message = "Content title must not be blank on null.")
    private String title;
    @NotBlank(message = "Content body must not be blank on null.")
    private String body;
}
