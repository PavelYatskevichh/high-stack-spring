package com.yatskevich.hs.spring.content_creation.api_client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:content-creation-api-client.properties")
@EnableFeignClients
@ConditionalOnMissingBean(ContentFeign.class)
public class ContentCreationApiClientConfig {
}
