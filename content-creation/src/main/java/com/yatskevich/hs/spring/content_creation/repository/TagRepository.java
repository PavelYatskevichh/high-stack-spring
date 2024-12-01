package com.yatskevich.hs.spring.content_creation.repository;

import com.yatskevich.hs.spring.content_creation.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface TagRepository extends JpaRepository<Tag, UUID> {
}
