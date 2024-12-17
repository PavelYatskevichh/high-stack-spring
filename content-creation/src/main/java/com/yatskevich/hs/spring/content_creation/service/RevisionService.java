package com.yatskevich.hs.spring.content_creation.service;

import com.yatskevich.hs.spring.content_creation.dto.RevisionDataDto;
import com.yatskevich.hs.spring.content_creation.dto.RevisionDto;
import java.util.List;
import java.util.UUID;

public interface RevisionService {
    List<RevisionDto> getAllForContent(UUID contentId, UUID authorId);

    void create(RevisionDataDto revisionDataDto, UUID authorId);
}
