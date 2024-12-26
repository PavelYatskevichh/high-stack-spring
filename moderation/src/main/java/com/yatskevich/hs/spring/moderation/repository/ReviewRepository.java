package com.yatskevich.hs.spring.moderation.repository;

import com.yatskevich.hs.spring.moderation.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
