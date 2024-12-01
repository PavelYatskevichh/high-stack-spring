package com.yatskevich.hs.spring.content_creation.repository;

import com.yatskevich.hs.spring.content_creation.entity.Revision;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface RevisionRepository extends JpaRepository<Revision, UUID> {
}
