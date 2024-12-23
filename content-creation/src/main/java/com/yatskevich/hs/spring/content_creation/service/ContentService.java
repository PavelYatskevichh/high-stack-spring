package com.yatskevich.hs.spring.content_creation.service;

import com.yatskevich.hs.spring.content_creation.dto.ContentDataDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentTagsDto;
import com.yatskevich.hs.spring.content_creation.entity.Content;
import java.util.List;
import java.util.UUID;

public interface ContentService {

    List<ContentDto> getAll(UUID authorId);

    ContentDto getById(UUID contentId, UUID authorId);

    Content findByIdAndAuthorIdOrElseThrow(UUID contentId, UUID authorId);

    void create(ContentDataDto contentDataDto, UUID authorId);

    void addTags(ContentTagsDto contentTagsDto, UUID authorId);

    void deleteTags(ContentTagsDto contentTagsDto, UUID authorId);
}
