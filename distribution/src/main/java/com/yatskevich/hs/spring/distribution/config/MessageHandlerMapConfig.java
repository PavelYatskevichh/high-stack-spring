package com.yatskevich.hs.spring.distribution.config;

import com.yatskevich.hs.spring.distribution.handler.MessageHandler;
import com.yatskevich.hs.spring.kafka_messaging.constant.MessageType;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageHandlerMapConfig {

    @Bean
    public Map<MessageType, MessageHandler> messageHandlerMap(List<MessageHandler> messageHandlers) {
        return messageHandlers.stream()
            .collect(Collectors.toMap(
                MessageHandler::getMessageType,
                Function.identity(),
                (h1, h2) -> {
                    throw new IllegalArgumentException(
                        "Two equal MessageHandler: " + h1.getMessageType() + " and " + h2.getMessageType()
                    );
                },
                () -> new EnumMap<>(MessageType.class)
            ));
    }
}
