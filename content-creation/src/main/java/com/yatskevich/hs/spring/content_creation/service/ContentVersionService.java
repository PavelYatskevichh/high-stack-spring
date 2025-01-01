package com.yatskevich.hs.spring.content_creation.service;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.content_creation.api_client.dto.RevisionDataDto;
import com.yatskevich.hs.spring.content_creation.api_client.dto.RevisionDto;
import java.util.List;
import java.util.UUID;

public interface ContentVersionService {

    List<RevisionDto> getAllByContentAndAuthor(UUID contentId, UUID authorId);

    void createRevision(RevisionDataDto revisionDataDto, UUID authorId);

    void updateStatus(ContentStatusDto contentStatusDto);
}
