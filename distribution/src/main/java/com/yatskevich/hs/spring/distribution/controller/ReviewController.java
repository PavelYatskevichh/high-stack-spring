package com.yatskevich.hs.spring.distribution.controller;

import com.yatskevich.hs.spring.distribution.dto.PublicationDataDto;
import com.yatskevich.hs.spring.distribution.dto.PublicationDto;
import com.yatskevich.hs.spring.distribution.service.PublicationService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/v1/reviews")
public class ReviewController {

    private final PublicationService publicationService;

    @GetMapping
    public List<PublicationDto> getAll() {
        log.debug("Getting all the reviews.");
        return publicationService.getAll();
    }

    @GetMapping("/{id}")
    public PublicationDto getById(@PathVariable("id") UUID publicationId) {
        log.debug("Getting the review {}.", publicationId);
        return publicationService.getById(publicationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam UUID moderatorId,
                       @RequestBody @Valid PublicationDataDto publicationDataDto) {
        log.debug("Creating new review for the content {} by reviewer {}.", publicationDataDto.getContentId(), moderatorId);
        publicationService.create(publicationDataDto, moderatorId);
    }
}
