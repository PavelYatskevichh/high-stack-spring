package com.yatskevich.hs.spring.content_creation.handler.impl;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.content_creation.handler.MessageHandler;
import com.yatskevich.hs.spring.content_creation.service.ContentVersionService;
import com.yatskevich.hs.spring.kafka_messaging.constant.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateContentStatusMessageHandler implements MessageHandler {

    private final ContentVersionService contentVersionService;

    @Override
    public void handleMessage(ContentStatusDto contentStatusDto) {
        contentVersionService.updateStatus(contentStatusDto);
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.UPDATE_CONTENT_STATUS;
    }
}
