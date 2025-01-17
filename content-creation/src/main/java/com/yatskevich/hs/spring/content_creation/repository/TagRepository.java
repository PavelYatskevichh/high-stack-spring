package com.yatskevich.hs.spring.content_creation.repository;

import com.yatskevich.hs.spring.content_creation.entity.Tag;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, UUID> {
}
