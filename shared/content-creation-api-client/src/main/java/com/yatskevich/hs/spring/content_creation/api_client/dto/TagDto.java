package com.yatskevich.hs.spring.content_creation.api_client.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDto {
    private UUID id;
    private String name;
}
