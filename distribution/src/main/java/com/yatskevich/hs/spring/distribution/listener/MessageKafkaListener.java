package com.yatskevich.hs.spring.distribution.listener;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.distribution.service.PublicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageKafkaListener {

    private final PublicationService publicationService;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}",
        containerFactory = "kafkaListenerContainerFactory")
    public void listenMessage(@Payload ContentStatusDto contentStatusDto,
                              @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        try {
            log.debug("Getting message with key {}", key);
            publicationService.create(contentStatusDto);
        } catch (Exception e) {
            log.error("Exception during message handling", e);
        }
    }
}
