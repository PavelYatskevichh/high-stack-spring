package com.yatskevich.hs.spring.content_creation.repository;

import com.yatskevich.hs.spring.content_creation.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
}
