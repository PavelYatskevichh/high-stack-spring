package com.yatskevich.hs.spring.moderation.service;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;

public interface KafkaService {

    void sendMessageUpdateContentStatus(ContentStatusDto contentStatusDto);
}
