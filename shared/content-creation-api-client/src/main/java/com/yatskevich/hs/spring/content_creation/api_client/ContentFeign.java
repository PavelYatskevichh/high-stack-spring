package com.yatskevich.hs.spring.content_creation.api_client;

import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentDto;
import com.yatskevich.hs.spring.content_creation.api_client.dto.ContentStatusDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "contentCreation", url = "${feign.content-creation.url}")
public interface ContentFeign {

    @GetMapping("${feign.content-creation.uri.get-content}" + "/{id}")
    ContentDto getById(@PathVariable("id") UUID contentId);

    @PutMapping("${feign.content-creation.uri.update-status}")
    void updateStatus(@RequestBody ContentStatusDto contentStatusDto);
}
