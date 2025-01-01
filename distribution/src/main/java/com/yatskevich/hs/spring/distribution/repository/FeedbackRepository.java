package com.yatskevich.hs.spring.distribution.repository;

import com.yatskevich.hs.spring.distribution.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
}
