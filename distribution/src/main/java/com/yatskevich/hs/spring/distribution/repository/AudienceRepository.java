package com.yatskevich.hs.spring.distribution.repository;

import com.yatskevich.hs.spring.distribution.entity.Audience;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudienceRepository extends JpaRepository<Audience, UUID> {
}
