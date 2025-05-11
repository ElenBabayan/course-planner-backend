package com.aua.courseplanner.controller;

import com.aua.courseplanner.entity.Session;
import com.aua.courseplanner.service.SessionService;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;


@RestController
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * POST /session/createSession
     * Creates a new chat session and returns the generated session ID.
     */
    @PostMapping("/createSession")
    public ResponseEntity<Map<String, UUID>> createSession() {
        Session newSession = sessionService.createNewSession();
        UUID id = newSession.getSessionID();
        // Return the sessionID in JSON format
        return ResponseEntity.ok(Collections.singletonMap("sessionID", id));
    }

    /**
     * GET /session/{sessionID}
     * Retrieves all messages for the session as a JSON array.
     */
    @GetMapping("/{sessionID}")
    public ResponseEntity<String> getSessionMessages(@PathVariable UUID sessionID) {
        String messagesJson = sessionService.getSessionMessages(sessionID);
        // Return the JSON string of messages with content type application/json
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(messagesJson);
    }
}
