package com.yatskevich.hs.spring.content_creation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yatskevich.hs.spring.content_creation.AbstractTestcontainersTests;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    @Sql({"classpath:scripts/insert_data_for_content_with_tags.sql"})
    void getAll_ReturnAll() throws Exception {
        UUID authorId = UUID.fromString("00000000-dddd-0000-0000-000000000001");
        List<String> contentIds = List.of(
            "11111111-dddd-1111-1111-111111111111", "11111111-dddd-1111-1111-111111111112");

        mockMvc.perform(get("/api/v1/contents").requestAttr("authorId", authorId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.list.content[*].id").value(contentIds));
    }
}
