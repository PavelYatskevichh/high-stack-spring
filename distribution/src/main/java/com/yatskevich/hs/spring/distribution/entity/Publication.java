package com.yatskevich.hs.spring.distribution.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publications")
public class Publication {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "content_item_id", nullable = false)
    private UUID contentItemId;

    @Column(name = "moderator_id", nullable = false)
    private UUID moderatorId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @CreationTimestamp
    @Column(name = "published_at", nullable = false, updatable = false)
    private LocalDateTime publishedAt;

}
