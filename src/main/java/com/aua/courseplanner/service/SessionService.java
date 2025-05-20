package com.aua.courseplanner.service;

import com.aua.courseplanner.dto.MessageRequest;
import com.aua.courseplanner.entity.Session;
import com.aua.courseplanner.repository.SessionRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class SessionService {
    private final SessionRepository sessionRepo;

    public SessionService(SessionRepository sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

    /**
     * Create a new session with an initial system prompt message.
     */
    public Session createNewSession() {
        Session session = new Session();
        return sessionRepo.save(session);
    }

    /**
     * Get all messages for an existing session by ID.
     */
    public MessageRequest getSessionMessages(UUID sessionId) {
        Session session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new NoSuchElementException("Session not found"));
        return session.getMessages();
    }

    /**
     * Post a message to an existing session and return the updated messages.
     */
    public MessageRequest postSessionMessage(UUID sessionId, MessageRequest message) {
        Session session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new NoSuchElementException("Session not found"));
        session.setMessages(message);
        Session savedSession = sessionRepo.save(session);
        return savedSession.getMessages();
    }
}