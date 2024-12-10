package com.yatskevich.hs.spring.content_creation.service.impl;

import com.yatskevich.hs.spring.content_creation.dto.ContentDataDto;
import com.yatskevich.hs.spring.content_creation.entity.Content;
import com.yatskevich.hs.spring.content_creation.entity.ContentStatus;
import com.yatskevich.hs.spring.content_creation.repository.ContentRepository;
import com.yatskevich.hs.spring.content_creation.service.ContentService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    @Override
    public void create(ContentDataDto contentDataDto, UUID authorId) {
        Content content = Content.builder()
            .title(contentDataDto.getTitle())
            .description(contentDataDto.getDescription())
            .body(contentDataDto.getBody())
            .authorId(authorId)
            .status(ContentStatus.DRAFT)
            .build();

        log.debug("Saving new content {} by {} to the database.", contentDataDto.getTitle(), authorId);
        contentRepository.save(content);
    }
}
