package com.yatskevich.hs.spring.content_creation.handler;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.kafka_messaging.constant.MessageType;

public interface MessageHandler {

    void handleMessage(ContentStatusDto contentStatusDto);

    MessageType getMessageType();
}
