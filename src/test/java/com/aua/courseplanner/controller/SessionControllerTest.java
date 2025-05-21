package com.aua.courseplanner.controller;


import com.aua.courseplanner.dto.Message;
import com.aua.courseplanner.dto.MessageRequest;
import com.aua.courseplanner.entity.Session;
import com.aua.courseplanner.service.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SessionController.class)
class SessionControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    SessionService sessionService;

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
        MessageRequest messageRequest = new MessageRequest();
        when(sessionService.getSessionMessages(id)).thenReturn(messageRequest);

        mvc.perform(get("/session/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));
    }

    @Test
    void getAllSessionIDs_returnsList() throws Exception {
        UUID id = UUID.randomUUID();
        when(sessionService.getAllSessionIDs()).thenReturn(List.of(id));

        mvc.perform(get("/session/sessions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(String.format("[\"%s\"]", id)));
    }


    @Test
    void postSessionMessage_returnsUpdatedJson() throws Exception {
        UUID id = UUID.randomUUID();

        MessageRequest messageRequest = new MessageRequest();
        Message message = new Message();
        message.setRole("system");
        message.setContent("content");
        message.setParts(List.of("parts"));
        messageRequest.setMessages(List.of(message));

        when(sessionService.postSessionMessage(eq(id), any(MessageRequest.class))).thenReturn(messageRequest);

        mvc.perform(post("/session/" + id + "/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"role\":\"system\", \"content\":\"Hello\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                            {
                              "messages": [
                                {
                                  "role": "system",
                                  "content": "content",
                                  "parts": ["parts"]
                                }
                              ]
                            }
                        """));
    }
}