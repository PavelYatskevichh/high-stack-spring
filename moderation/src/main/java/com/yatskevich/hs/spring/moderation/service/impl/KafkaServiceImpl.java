package com.yatskevich.hs.spring.moderation.service.impl;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import com.yatskevich.hs.spring.kafka_messaging.constant.MessageType;
import com.yatskevich.hs.spring.moderation.service.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value(value = "${kafka.topic}")
    private String kafkaTopic;

    @Override
    public void sendMessageUpdateContentStatus(ContentStatusDto contentStatusDto) {
        log.debug("Sending {} message for content {} to the kafka.",
            MessageType.UPDATE_CONTENT_STATUS.name(), contentStatusDto.getId());
        kafkaTemplate.send(kafkaTopic, MessageType.UPDATE_CONTENT_STATUS.name(), contentStatusDto);
    }
}
