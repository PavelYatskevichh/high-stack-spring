package com.yatskevich.hs.spring.content_creation.controller;

import com.yatskevich.hs.spring.content_creation.dto.ContentDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentStatusDto;
import com.yatskevich.hs.spring.content_creation.dto.ContentTagsDto;
import com.yatskevich.hs.spring.content_creation.service.ContentService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
public class ContentController {

    private final ContentService contentService;

    @GetMapping
    public void getAllContents(@RequestParam UUID authorId) {
        log.debug("Getting all the content of author {}.", authorId);
        contentService.getAll(authorId);
    }

    @GetMapping("/{id}")
    public void getContentById(@RequestParam UUID authorId,
                               @PathVariable UUID contentId) {
        log.debug("Getting content with ID {} of author {}.", contentId, authorId);
        contentService.getById(contentId, authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContent(@RequestParam UUID authorId,
                              @RequestBody @Valid ContentDto contentDto) {
        log.debug("Creating new content {} by author {}.", contentDto.getTitle(), authorId);
        contentService.createContent(contentDto, authorId);
    }

    //TODO decide add or update tags
    @PatchMapping("/{id}/tags")
    public void addTags(@RequestParam UUID authorId,
                        @RequestBody @Valid ContentTagsDto contentTagsDto) {
        log.debug("Adding tags {} to the content {} by author {}.",
            contentTagsDto.getTagIds(), contentTagsDto.getId(), authorId);
        contentService.addTags(contentTagsDto, authorId);
    }

    @PatchMapping("/{id}/status")
    public void updateContentStatus(@RequestParam UUID authorId,
                                    @RequestBody @Valid ContentStatusDto contentStatusDto) {
        log.debug("Change status to {} of the content with ID {} by author {}.",
            contentStatusDto.getStatus(), contentStatusDto.getId(), authorId);
        contentService.updateContentStatus(contentStatusDto, authorId);
    }
}
