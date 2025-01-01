package com.yatskevich.hs.spring.distribution.mapper;

import com.yatskevich.hs.spring.distribution.dto.PublicationDto;
import com.yatskevich.hs.spring.distribution.entity.Publication;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    PublicationDto toDto(Publication publication);

    List<PublicationDto> toDtoList(List<Publication> publications);
}
