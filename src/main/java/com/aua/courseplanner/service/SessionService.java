package com.aua.courseplanner.service;

import com.aua.courseplanner.entity.Session;
import com.aua.courseplanner.repository.SessionRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

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
    public String getSessionMessages(UUID sessionId) {
        Session session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new NoSuchElementException("Session not found"));
        return session.getMessages();
    }
}