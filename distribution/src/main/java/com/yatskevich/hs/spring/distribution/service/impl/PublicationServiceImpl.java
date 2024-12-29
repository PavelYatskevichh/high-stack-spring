package com.yatskevich.hs.spring.distribution.service.impl;

import com.yatskevich.hs.spring.content_creation.api_client.ContentCreationFeign;
import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentDto;
import com.yatskevich.hs.spring.distribution.dto.PublicationDataDto;
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

    private final PublicationRepository publicationRepository;
    private final PublicationMapper publicationMapper;
    private final ContentCreationFeign contentCreationFeign;

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
    public void create(PublicationDataDto publicationDataDto, UUID moderatorId) {
        UUID contentId = publicationDataDto.getContentId();
        ContentDto content = contentCreationFeign.getById(contentId);

        Publication publication = Publication.builder()
            .contentId(contentId)
            .moderatorId(moderatorId)
            .title(content.getTitle())
            .body(content.getBody())
            .build();

        publicationRepository.save(publication);
    }
}
