package com.yatskevich.hs.spring.content_creation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yatskevich.hs.spring.content_creation.AbstractTestcontainersTests;
import com.yatskevich.hs.spring.content_creation.FileUtils;
import com.yatskevich.hs.spring.content_creation.entity.Content;
import com.yatskevich.hs.spring.content_creation.repository.ContentRepository;
import jakarta.validation.ValidationException;
import java.util.Comparator;
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

    public static final String USER_ID = "00000000-dddd-0000-0000-000000000001";
    public static final String CONTENT_ID_1 = "11111111-dddd-1111-1111-111111111111";
    public static final String CONTENT_ID_2 = "11111111-dddd-1111-1111-111111111112";
    public static final String NEW_CONTENT_TITLE = "Content Title";
    public static final String NEW_CONTENT_DESCRIPTION = "Content Description";
    public static final String NEW_CONTENT_BODY = "Content Body";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContentRepository contentRepository;

    @Test
    @Sql({"classpath:scripts/insert_data_for_content_with_tags.sql"})
    void getAll_ContentsExist_ReturnStatusOkAndAllContents() throws Exception {
        mockMvc.perform(get("/v1/contents").param("authorId", USER_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[*].id", Matchers.containsInAnyOrder(CONTENT_ID_1, CONTENT_ID_2)));
    }

    @Test
    @Sql({"classpath:scripts/insert_data_for_content_with_tags.sql"})
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
        Optional<Content> optional = contents.stream().max(Comparator.comparing(Content::getCreatedAt));
        assertThat(optional).isPresent();
        assertEquals(NEW_CONTENT_TITLE, optional.get().getTitle());
        assertEquals(NEW_CONTENT_DESCRIPTION, optional.get().getDescription());
        assertEquals(NEW_CONTENT_BODY, optional.get().getBody());
    }

//    @Test
//    @Sql({"classpath:scripts/insert_data_for_content_with_tags.sql"})
//    void addTags_ValidRequestBody_ReturnStatusOk() throws Exception {
//        mockMvc.perform(
//                post("/v1/contents/tags").param("authorId", USER_ID)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(FileUtils.getJsonAsString("classpath:json/createContent_request.json"))
//            )
//            .andExpect(status().isOk());
//
//        List<Content> contents = contentRepository.findAllByAuthorId(UUID.fromString(USER_ID));
//        assertThat(contents).isNotNull();
//        Optional<Content> optional = contents.stream().max(Comparator.comparing(Content::getCreatedAt));
//        assertThat(optional).isPresent();
//        assertEquals(NEW_CONTENT_TITLE, optional.get().getTitle());
//        assertEquals(NEW_CONTENT_DESCRIPTION, optional.get().getDescription());
//        assertEquals(NEW_CONTENT_BODY, optional.get().getBody());
//    }
}
