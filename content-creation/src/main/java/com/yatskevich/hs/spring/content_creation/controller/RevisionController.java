package com.yatskevich.hs.spring.content_creation.controller;

import com.yatskevich.hs.spring.content_creation.dto.RevisionDto;
import com.yatskevich.hs.spring.content_creation.service.RevisionService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/revisions")
public class RevisionController {

    private final RevisionService revisionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContent(@RequestParam UUID authorId,
                              @RequestBody @Valid RevisionDto revisionDto) {
        log.debug("Creating new revision for content {} by author {}.", revisionDto.getContentId(), authorId);
        revisionService.createRevision(revisionDto, authorId);
    }
}
