package com.yatskevich.hs.spring.content_creation.api_client;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "contentCreation", url = "${feign.content-creation.url}")
public interface ContentCreationFeign {

    @GetMapping("${feign.content-creation.uri.get-content}" + "/{id}")
    ContentDto getById(@PathVariable("id") UUID contentId);
}
