package com.yatskevich.hs.spring.distribution.service;

import com.yatskevich.hs.spring.distribution.dto.PublicationDataDto;
import com.yatskevich.hs.spring.distribution.dto.PublicationDto;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface PublicationService {
    List<PublicationDto> getAll();

    PublicationDto getById(UUID publicationId);

    void create(PublicationDataDto publicationDataDto, UUID moderatorId);
}
