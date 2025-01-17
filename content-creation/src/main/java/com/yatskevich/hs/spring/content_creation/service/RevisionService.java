package com.yatskevich.hs.spring.content_creation.service;

import com.yatskevich.hs.spring.content_creation.api_client.dto.RevisionDataDto;
import com.yatskevich.hs.spring.content_creation.entity.Content;
import com.yatskevich.hs.spring.content_creation.entity.Revision;
import java.util.List;
import java.util.UUID;

public interface RevisionService {

    List<Revision> getAllByContentIdAndContentAuthorId(UUID contentId, UUID authorId);

    void deleteById(UUID contentId);

    Revision findLastByContentAndAuthor(UUID contentId, UUID authorId);

    void create(Content content, RevisionDataDto revisionDataDto);
}
