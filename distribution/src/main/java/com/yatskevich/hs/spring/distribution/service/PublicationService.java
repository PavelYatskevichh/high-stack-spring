package com.yatskevich.hs.spring.distribution.service;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.distribution.dto.PublicationDto;
import java.util.List;
import java.util.UUID;

public interface PublicationService {
    List<PublicationDto> getAll();

    PublicationDto getById(UUID publicationId);

    void create(ContentStatusDto contentStatusDto);
}
