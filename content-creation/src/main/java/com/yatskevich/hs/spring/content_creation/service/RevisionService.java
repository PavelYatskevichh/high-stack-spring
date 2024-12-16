package com.yatskevich.hs.spring.content_creation.service;

import com.yatskevich.hs.spring.content_creation.dto.RevisionDataDto;
import java.util.UUID;

public interface RevisionService {
    void getAll(UUID contentId, UUID authorId);

    void create(RevisionDataDto revisionDataDto, UUID authorId);
}
