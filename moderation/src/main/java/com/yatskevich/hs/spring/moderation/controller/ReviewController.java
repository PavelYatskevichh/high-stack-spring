package com.yatskevich.hs.spring.moderation.controller;

import com.yatskevich.hs.spring.moderation.dto.ReviewDataDto;
import com.yatskevich.hs.spring.moderation.dto.ReviewDto;
import com.yatskevich.hs.spring.moderation.service.ReviewService;
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

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDto> getAll() {
        log.debug("Getting all the reviews.");
        return reviewService.getAll();
    }

    @GetMapping("/{id}")
    public ReviewDto getById(@PathVariable("id") UUID reviewId) {
        log.debug("Getting the review {}.", reviewId);
        return reviewService.getById(reviewId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam UUID reviewerId,
                       @RequestBody @Valid ReviewDataDto reviewDataDto) {
        log.debug("Creating new review for the content {} by reviewer {}.", reviewDataDto.getContentId(), reviewerId);
        reviewService.create(reviewDataDto, reviewerId);
    }
}
