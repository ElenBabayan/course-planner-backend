package com.aua.courseplanner.controller;


import com.aua.courseplanner.entity.Session;
import com.aua.courseplanner.service.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = SessionController.class)
class SessionControllerTest {
    @Autowired MockMvc mvc;
    @MockBean SessionService sessionService;

    @Test
    void createSession_returnsGeneratedId() throws Exception {
        Session s = new Session();
        s.setSessionID(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        when(sessionService.createNewSession()).thenReturn(s);

        mvc.perform(post("/session/createSession"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sessionID").value("550e8400-e29b-41d4-a716-446655440000"));
    }

    @Test
    void getSessionMessages_returnsJsonArray() throws Exception {
        UUID id = UUID.randomUUID();
        when(sessionService.getSessionMessages(id)).thenReturn("[]");

        mvc.perform(get("/session/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }
}