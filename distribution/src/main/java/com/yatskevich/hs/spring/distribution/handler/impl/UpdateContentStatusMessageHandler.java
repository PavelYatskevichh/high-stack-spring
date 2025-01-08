package com.yatskevich.hs.spring.distribution.handler.impl;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.distribution.handler.MessageHandler;
import com.yatskevich.hs.spring.distribution.service.PublicationService;
import com.yatskevich.hs.spring.kafka_messaging.constant.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateContentStatusMessageHandler implements MessageHandler {

    private final PublicationService publicationService;

    @Override
    public void handleMessage(ContentStatusDto contentStatusDto) {
        publicationService.create(contentStatusDto);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.UPDATE_CONTENT_STATUS;
    }
}
