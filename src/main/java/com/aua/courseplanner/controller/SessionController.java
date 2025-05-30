package com.aua.courseplanner.controller;

import com.aua.courseplanner.dto.MessageRequest;
import com.aua.courseplanner.entity.Session;
import com.aua.courseplanner.service.SessionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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
        return ResponseEntity.ok(Collections.singletonMap("sessionID", id));
    }

    /**
     * GET /session/{sessionID}
     * Retrieves all messages for the session as a JSON array.
     */
    @GetMapping("/{sessionID}")
    public ResponseEntity<MessageRequest> getSessionMessages(@PathVariable UUID sessionID) {
        MessageRequest messagesJson = sessionService.getSessionMessages(sessionID);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(messagesJson);
    }

    /**
     * GET /sessions
     * Retrieves all sessionIDs as a list.
     */
    @GetMapping("/sessions")
    public ResponseEntity<List<UUID>> getAllSessionIDs() {
        List<UUID> sessionIDs = sessionService.getAllSessionIDs();
        return ResponseEntity.ok(sessionIDs);
    }

    /**
     * POST /session/{sessionID}/messages
     * Posts a message to the session and returns the updated message as a JSON array.
     */
    @PostMapping("/{sessionID}/messages")
    public ResponseEntity<MessageRequest> postSessionMessage(@RequestBody MessageRequest message,
                                             @PathVariable UUID sessionID) {
        MessageRequest updatedMessagesJson = sessionService.postSessionMessage(sessionID, message);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(updatedMessagesJson);
    }
}
