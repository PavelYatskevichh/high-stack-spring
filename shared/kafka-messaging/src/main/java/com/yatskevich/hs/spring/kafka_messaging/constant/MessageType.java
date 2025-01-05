package com.yatskevich.hs.spring.kafka_messaging.constant;

public enum MessageType {
    UPDATE_CONTENT_STATUS;

    public static MessageType findByNameOrNull(String name) {
        for (MessageType messageType : values()) {
            if (messageType.name().equalsIgnoreCase(name)) {
                return messageType;
            }
        }
        return null;
    }
}
