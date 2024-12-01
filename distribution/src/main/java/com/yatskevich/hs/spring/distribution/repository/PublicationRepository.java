package com.yatskevich.hs.spring.distribution.repository;

import com.yatskevich.hs.spring.distribution.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface PublicationRepository extends JpaRepository<Publication, UUID> {
}
