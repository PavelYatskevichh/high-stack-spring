package com.yatskevich.hs.spring.content_creation.service.impl;

import com.yatskevich.hs.spring.content_creation.dto.ContentDataDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentStatusDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentTagsDto;
import com.yatskevich.hs.spring.content_creation.entity.Content;
import com.yatskevich.hs.spring.content_creation.entity.ContentStatus;
import com.yatskevich.hs.spring.content_creation.entity.Tag;
import com.yatskevich.hs.spring.content_creation.mapper.ContentMapper;
import com.yatskevich.hs.spring.content_creation.repository.ContentRepository;
import com.yatskevich.hs.spring.content_creation.repository.TagRepository;
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
    private final TagRepository tagRepository;

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
            //FIXME create exception
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
        log.debug("Changing the status of the content {} to {} in the database.",
            contentStatusDto.getId(), contentStatusDto.getStatus());
        contentRepository.updateStatus(contentStatusDto.getId(), contentStatusDto.getStatus());
    }

    @Override
    public void addTags(ContentTagsDto contentTagsDto, UUID authorId) {

        List<Tag> tags = tagRepository.findAllById(contentTagsDto.getTagIds())
    }

    @Override
    public void deleteTags(ContentTagsDto contentTagsDto, UUID authorId) {

    }
}
