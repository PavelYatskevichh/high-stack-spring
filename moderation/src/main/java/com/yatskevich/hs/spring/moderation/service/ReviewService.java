package com.yatskevich.hs.spring.moderation.service;

import com.yatskevich.hs.spring.moderation.dto.ReviewDataDto;
import com.yatskevich.hs.spring.moderation.dto.ReviewDto;
import java.util.List;
import java.util.UUID;

public interface ReviewService {
    List<ReviewDto> getAll();

    ReviewDto getById(UUID reviewId);

    void create(ReviewDataDto reviewDataDto, UUID reviewerId);
}
