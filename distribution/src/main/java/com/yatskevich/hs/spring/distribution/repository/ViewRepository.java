package com.yatskevich.hs.spring.distribution.repository;

import com.yatskevich.hs.spring.distribution.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViewRepository extends JpaRepository<View, UUID> {
}
