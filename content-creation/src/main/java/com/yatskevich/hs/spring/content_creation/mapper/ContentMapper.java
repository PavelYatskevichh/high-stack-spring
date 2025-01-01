package com.yatskevich.hs.spring.content_creation.mapper;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentDto;
import com.yatskevich.hs.spring.content_creation.entity.Content;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
    uses = {TagMapper.class})
public interface ContentMapper {

    ContentDto toDto(Content content);

    List<ContentDto> toDtoList(List<Content> contents);
}
