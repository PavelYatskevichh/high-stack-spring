package com.yatskevich.hs.spring.content_creation.service;

import com.yatskevich.hs.spring.content_creation.dto.ContentRequestDto;
import java.util.UUID;

public interface ContentService {

    void createContent(ContentRequestDto contentDto, UUID authorId);
}
