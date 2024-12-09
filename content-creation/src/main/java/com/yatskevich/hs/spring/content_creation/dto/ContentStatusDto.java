package com.yatskevich.hs.spring.content_creation.dto;

import com.yatskevich.hs.spring.content_creation.entity.ContentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentStatusDto {
    @NotNull(message = "Content ID must not be null.")
    private UUID id;
    @Pattern(regexp = "(?i)(draft)|(submitted)|(approved)|(rejected)", message = "Provided content status not exists.")
    private ContentStatus status;
}
