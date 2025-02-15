package com.yatskevich.hs.spring.distribution.service.impl;

import com.yatskevich.hs.spring.content_creation.api_client.ContentFeign;
import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentDto;
import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.distribution.dto.PublicationDto;
import com.yatskevich.hs.spring.distribution.entity.Publication;
import com.yatskevich.hs.spring.distribution.mapper.PublicationMapper;
import com.yatskevich.hs.spring.distribution.repository.PublicationRepository;
import com.yatskevich.hs.spring.distribution.service.PublicationService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PublicationServiceImpl implements PublicationService {

    private static final String CONTENT_STATUS_APPROVED = "APPROVED";
    private final PublicationRepository publicationRepository;
    private final PublicationMapper publicationMapper;
    private final ContentFeign contentFeign;

    @Override
    @Transactional(readOnly = true)
    public List<PublicationDto> getAll() {
        log.debug("Searching for all the publications in the database.");
        return publicationMapper.toDtoList(publicationRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public PublicationDto getById(UUID publicationId) {
        return publicationMapper.toDto(findByIdOrElseThrow(publicationId));
    }

    private Publication findByIdOrElseThrow(UUID reviewId) {
        log.debug("Searching for the publication {} in the database.", reviewId);
        return publicationRepository.findById(reviewId).orElseThrow(() -> {
            log.error("The publication {} is not found in the database.", reviewId);
            //FIXME create exception
            return new RuntimeException("The publication %s is not found in the database."
                .formatted(reviewId));
        });
    }

    @Override
    public void create(ContentStatusDto contentStatusDto) {
        if (!contentStatusDto.getStatus().equalsIgnoreCase(CONTENT_STATUS_APPROVED)) {
            return;
        }
        UUID contentId = contentStatusDto.getId();
        ContentDto content = contentFeign.getById(contentId);

        Publication publication = Publication.builder()
            .contentId(contentId)
            .title(content.getTitle())
            .body(content.getBody())
            .build();

        publicationRepository.save(publication);
    }
}
