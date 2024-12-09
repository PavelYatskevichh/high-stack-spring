package com.yatskevich.hs.spring.content_creation.service;

import com.yatskevich.hs.spring.content_creation.dto.RevisionDto;
import java.util.UUID;

public interface RevisionService {
    void createRevision(RevisionDto revisionDto, UUID authorId);
}
