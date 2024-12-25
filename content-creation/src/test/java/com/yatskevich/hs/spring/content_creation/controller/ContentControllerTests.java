package com.yatskevich.hs.spring.content_creation.controller;

import static com.yatskevich.hs.spring.content_creation.TestUtils.CONTENT_BODY;
import static com.yatskevich.hs.spring.content_creation.TestUtils.CONTENT_DESCRIPTION;
import static com.yatskevich.hs.spring.content_creation.TestUtils.CONTENT_ID_1;
import static com.yatskevich.hs.spring.content_creation.TestUtils.CONTENT_ID_2;
import static com.yatskevich.hs.spring.content_creation.TestUtils.CONTENT_TITLE;
import static com.yatskevich.hs.spring.content_creation.TestUtils.TAG_ID_1;
import static com.yatskevich.hs.spring.content_creation.TestUtils.TAG_ID_3;
import static com.yatskevich.hs.spring.content_creation.TestUtils.USER_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yatskevich.hs.spring.content_creation.AbstractTestcontainersTests;
import com.yatskevich.hs.spring.content_creation.FileUtils;
import com.yatskevich.hs.spring.content_creation.dto.ContentTagsDto;
import com.yatskevich.hs.spring.content_creation.entity.Content;
import com.yatskevich.hs.spring.content_creation.entity.Tag;
import com.yatskevich.hs.spring.content_creation.repository.ContentRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class ContentControllerTests extends AbstractTestcontainersTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql({"classpath:scripts/insert_contents.sql"})
    void getAll_ContentsExist_ReturnStatusOkAndAllContents() throws Exception {
        mockMvc.perform(get("/v1/contents").param("authorId", USER_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[*].id", Matchers.containsInAnyOrder(CONTENT_ID_1, CONTENT_ID_2)));
    }

    @Test
    @Sql({"classpath:scripts/insert_contents.sql"})
    void getById_ContentExist_ReturnStatusOkAndContent() throws Exception {
        mockMvc.perform(get("/v1/contents/{id}", CONTENT_ID_1).param("authorId", USER_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(CONTENT_ID_1));
    }

    @Test
    void create_InvalidRequestBody_ReturnStatusBadRequest() throws Exception {
        mockMvc.perform(
                post("/v1/contents").param("authorId", USER_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(FileUtils.getJsonAsString("classpath:json/createContent_invalidRequest.json"))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void create_ValidRequestBody_ReturnStatusCreated() throws Exception {
        mockMvc.perform(
                post("/v1/contents").param("authorId", USER_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(FileUtils.getJsonAsString("classpath:json/createContent_request.json"))
            )
            .andExpect(status().isCreated());

        List<Content> contents = contentRepository.findAllByAuthorId(UUID.fromString(USER_ID));
        assertThat(contents).isNotNull();
        assertThat(contents).hasSize(1);
        Content content = contents.getFirst();
        assertEquals(CONTENT_TITLE, content.getTitle());
        assertEquals(CONTENT_DESCRIPTION, content.getDescription());
        assertEquals(CONTENT_BODY, content.getBody());
    }

    @Test
    @Sql({"classpath:scripts/insert_contents.sql", "classpath:scripts/insert_tags.sql"})
    void addTags_ValidRequestBody_ReturnStatusOk() throws Exception {
        String json = FileUtils.getJsonAsString("classpath:json/addTagsToContent_request.json");

        mockMvc.perform(
                patch( "/v1/contents/tags").param("authorId", USER_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(status().isOk());

        ContentTagsDto contentTagsDto = objectMapper.readValue(json, ContentTagsDto.class);

        Optional<Content> optional = contentRepository
            .findByIdAndAuthorId(contentTagsDto.getId(), UUID.fromString(USER_ID));
        assertThat(optional).isPresent();
        assertEquals(optional.get().getTags().stream().map(Tag::getId).toList(), contentTagsDto.getTagIds());
    }

    @Test
    @Sql({"classpath:scripts/insert_contents.sql", "classpath:scripts/insert_tags.sql",
        "classpath:scripts/insert_content_and_tag_relations.sql"})
    void removeTags_ValidRequestBodyAndContentHasTags_ReturnStatusNoContent() throws Exception {
        String json = FileUtils.getJsonAsString("classpath:json/removeTagsFromContent_request.json");

        mockMvc.perform(
                delete( "/v1/contents/tags").param("authorId", USER_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(status().isNoContent());

        ContentTagsDto contentTagsDto = objectMapper.readValue(json, ContentTagsDto.class);

        Optional<Content> optional = contentRepository
            .findByIdAndAuthorId(contentTagsDto.getId(), UUID.fromString(USER_ID));
        assertThat(optional).isPresent();
        assertThat(optional.get().getTags().stream().map(Tag::getId).toList())
            .containsExactlyInAnyOrder(
                UUID.fromString(TAG_ID_1),
                UUID.fromString(TAG_ID_3)
            );
    }
}
