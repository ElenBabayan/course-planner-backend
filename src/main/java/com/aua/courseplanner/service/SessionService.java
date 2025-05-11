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
        // Initialize session with the system prompt message (Gemini prompt)
        String initialMessages = """
                [ {
                     "role": "system",
                     "content": "You are a university assistant. Based on the list of Courses and StudentProfile, generate an optimized SemesterPlan for the student.",
                     "parts": []
                   } ]
                """;
        Session session = new Session(initialMessages);
        return sessionRepo.save(session);
    }

    /**
     * Get all messages for an existing session by ID.
     */
    public String getSessionMessages(UUID sessionId) {
        Session session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new NoSuchElementException("Session not found"));
        return session.getMessages();  // returns the JSON string of messages
    }
}