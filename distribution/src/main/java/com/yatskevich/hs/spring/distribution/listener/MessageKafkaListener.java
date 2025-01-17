package com.yatskevich.hs.spring.distribution.listener;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.distribution.handler.MessageHandler;
import com.yatskevich.hs.spring.kafka_messaging.constant.MessageType;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}",
    containerFactory = "kafkaListenerContainerFactory")
public class MessageKafkaListener {

    private final Map<MessageType, MessageHandler> messageHandlerMap;

    @KafkaHandler
    public void listenMessage(@Payload ContentStatusDto contentStatusDto,
                              @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        try {
            log.debug("Getting message {} with key {}", contentStatusDto, key);
            MessageHandler messageHandler = messageHandlerMap.get(MessageType.findByNameOrNull(key));
            if (messageHandler == null) {
                log.error("Unknown kafka message key {} has been received. Message: {}.", key, contentStatusDto);
                return;
            }
            messageHandler.handleMessage(contentStatusDto);
        } catch (Exception e) {
            log.error("Exception during message handling", e);
        }
    }
}
