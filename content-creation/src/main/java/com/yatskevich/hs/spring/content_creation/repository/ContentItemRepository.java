package com.yatskevich.hs.spring.content_creation.repository;

import com.yatskevich.hs.spring.content_creation.entity.ContentItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ContentItemRepository extends JpaRepository<ContentItem, UUID> {
}
