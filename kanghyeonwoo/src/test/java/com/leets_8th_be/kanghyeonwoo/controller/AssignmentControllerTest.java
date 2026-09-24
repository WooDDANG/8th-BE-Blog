package com.leets_8th_be.kanghyeonwoo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /health 요청 시 200 OK 및 ok 문자열을 반환한다")
    void healthCheck() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @Test
    @DisplayName("POST /string/repeat 요청 시 문자열이 2개 복제된 JSON을 반환한다")
    void repeatString() throws Exception {
        String requestJson = "{\"value\":\"hello\"}";

        mockMvc.perform(post("/string/repeat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.string_one").value("hello"))
                .andExpect(jsonPath("$.string_two").value("hello"));
    }

    @Test
    @DisplayName("POST /string/repeat 요청 시 빈 문자열이 주어지면 빈 문자열 2개를 반환한다")
    void repeatEmptyString() throws Exception {
        String requestJson = "{\"value\":\"\"}";

        mockMvc.perform(post("/string/repeat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.string_one").value(""))
                .andExpect(jsonPath("$.string_two").value(""));
    }

    @Test
    @DisplayName("POST /string/repeat 요청 시 null 값이 주어지면 null 필드를 안전하게 반환한다")
    void repeatNullValue() throws Exception {
        String requestJson = "{\"value\":null}";

        mockMvc.perform(post("/string/repeat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.string_one").isEmpty())
                .andExpect(jsonPath("$.string_two").isEmpty());
    }
}
