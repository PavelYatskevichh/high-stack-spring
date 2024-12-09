package com.yatskevich.hs.spring.content_creation.service.impl;

import com.yatskevich.hs.spring.content_creation.dto.ContentDto;
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
    public void createContent(ContentDto contentDto, UUID authorId) {
        Content content = Content.builder()
            .title(contentDto.getTitle())
            .description(contentDto.getDescription())
            .body(contentDto.getBody())
            .authorId(authorId)
            .status(ContentStatus.DRAFT)
            .build();

        log.debug("Saving new content {} by {} to the database.", contentDto.getTitle(), authorId);
        contentRepository.save(content);
    }
}
