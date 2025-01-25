package com.yatskevich.hs.spring.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${contentCreationUrl}")
    private String contentCreationUrl;

    @Value("${moderationUrl}")
    private String moderationUrl;

    @Value("${distributionUrl}")
    private String distributionUrl;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("content-creation", p -> p
                .path("/api/v1/contents/**")
                .or().path("/api/v1/revisions/**")
                .uri(contentCreationUrl))
            .route("moderation", p -> p
                .path("/api/v1/reviews/**")
                .uri(moderationUrl))
            .route("distribution", p -> p
                .path("/api/v1/publications/**")
                .uri(distributionUrl))
            .build();
    }
}
