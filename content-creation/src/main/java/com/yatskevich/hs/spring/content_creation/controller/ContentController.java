package com.yatskevich.hs.spring.content_creation.controller;

import com.yatskevich.hs.spring.content_creation.dto.ContentRequestDto;
import com.yatskevich.hs.spring.content_creation.service.ContentService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTickets(@RequestParam UUID authorId,
                              @RequestBody ContentRequestDto contentDto) {
        log.debug("Creating new content {} by {}.", contentDto.getTitle(), authorId);
        contentService.createContent(contentDto, authorId);
    }
}
