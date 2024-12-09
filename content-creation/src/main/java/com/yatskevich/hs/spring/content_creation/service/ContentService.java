package com.yatskevich.hs.spring.content_creation.service;

import com.yatskevich.hs.spring.content_creation.dto.ContentDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentStatusDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentTagsDto;
import java.util.UUID;

public interface ContentService {

    void getAll(UUID authorId);

    void getById(UUID contentId, UUID authorId);

    void createContent(ContentDto contentDto, UUID authorId);

    void updateContentStatus(ContentStatusDto contentStatusDto, UUID authorId);

    void addTags(ContentTagsDto contentTagsDto, UUID authorId);
}
