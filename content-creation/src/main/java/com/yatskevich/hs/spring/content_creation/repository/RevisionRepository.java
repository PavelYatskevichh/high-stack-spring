package com.yatskevich.hs.spring.content_creation.repository;

import com.yatskevich.hs.spring.content_creation.entity.Content;
import com.yatskevich.hs.spring.content_creation.entity.Revision;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RevisionRepository extends JpaRepository<Revision, UUID> {

    List<Content> findAllByContentIdAndAuthorId(@Param("contentId") UUID contentId,
                                                @Param("authorId") UUID authorId);
}
