package com.yatskevich.hs.spring.distribution.repository;

import com.yatskevich.hs.spring.distribution.entity.Audience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AudienceRepository extends JpaRepository<Audience, UUID> {
}
