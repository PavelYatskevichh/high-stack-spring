package com.yatskevich.hs.spring.content_creation.service.impl;

import com.yatskevich.hs.spring.content_creation.dto.ContentDataDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentStatusDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentTagsDto;
import com.yatskevich.hs.spring.content_creation.entity.Content;
import com.yatskevich.hs.spring.content_creation.entity.ContentStatus;
import com.yatskevich.hs.spring.content_creation.mapper.ContentMapper;
import com.yatskevich.hs.spring.content_creation.repository.ContentRepository;
import com.yatskevich.hs.spring.content_creation.service.ContentService;
import java.util.List;
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
    private final ContentMapper contentMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ContentDto> getAll(UUID authorId) {
        log.debug("Searching all the content of the author {} in the database.", authorId);
        return contentMapper.toDtoList(contentRepository.findAllByAuthorId(authorId));
    }

    @Override
    @Transactional(readOnly = true)
    public ContentDto getById(UUID contentId, UUID authorId) {
        log.debug("Searching the content {} of the author {} in the database.", contentId, authorId);
        return contentMapper.toDto(findByIdAndAuthorIdOrElseThrow(contentId, authorId));
    }

    private Content findByIdAndAuthorIdOrElseThrow(UUID contentId, UUID authorId) {
        return contentRepository.findByIdAndAuthorId(contentId, authorId).orElseThrow(() -> {
            log.error("The content {} of the author {} is not found in the database.", contentId, authorId);
            //TODO create exception
            return new RuntimeException("The content %s of the author %s is not found in the database."
                .formatted(contentId, authorId));
        });
    }

    @Override
    public void create(ContentDataDto contentDataDto, UUID authorId) {
        Content content = Content.builder()
            .title(contentDataDto.getTitle())
            .description(contentDataDto.getDescription())
            .body(contentDataDto.getBody())
            .authorId(authorId)
            .status(ContentStatus.DRAFT)
            .build();

        log.debug("Saving new content {} by author {} to the database.", contentDataDto.getTitle(), authorId);
        contentRepository.save(content);
    }

    @Override
    public void updateContentStatus(ContentStatusDto contentStatusDto) {
        UUID contentId = contentStatusDto.getId();

        log.debug("Searching the content {} in the database.", contentId);
        Content content = contentRepository.findById(contentId).orElseThrow(() -> {
            log.error("The content {} is not found in the database.", contentId);
            //TODO create exception
            return new RuntimeException("The content %s is not found in the database."
                .formatted(contentId));
        });

        content.setStatus(contentStatusDto.getStatus());
        log.debug("Saving content {} with updated status {} to the database.",
            contentId, contentStatusDto.getStatus());
        contentRepository.save(content);
    }

    @Override
    public void addTags(ContentTagsDto contentTagsDto, UUID authorId) {

    }

    @Override
    public void deleteTags(ContentTagsDto contentTagsDto, UUID authorId) {

    }
}
