package com.yatskevich.hs.spring.distribution.controller;

import com.yatskevich.hs.spring.distribution.dto.PublicationDto;
import com.yatskevich.hs.spring.distribution.service.PublicationService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/publications")
public class PublicationController {

    private final PublicationService publicationService;

    @GetMapping
    public List<PublicationDto> getAll() {
        log.debug("Getting all the publications.");
        return publicationService.getAll();
    }

    @GetMapping("/{id}")
    public PublicationDto getById(@PathVariable("id") UUID publicationId) {
        log.debug("Getting the publication {}.", publicationId);
        return publicationService.getById(publicationId);
    }
}
