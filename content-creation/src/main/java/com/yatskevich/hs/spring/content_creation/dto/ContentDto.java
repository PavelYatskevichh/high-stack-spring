package com.yatskevich.hs.spring.content_creation.dto;

import com.yatskevich.hs.spring.content_creation.entity.ContentStatus;
import com.yatskevich.hs.spring.content_creation.entity.Tag;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentDto {
    private UUID id;
    private String title;
    private String description;
    private String body;
    private UUID authorId;
    private ContentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Tag> tags;
}
