package com.aua.courseplanner.service;

import com.aua.courseplanner.entity.Session;
import com.aua.courseplanner.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {
    @Mock
    SessionRepository sessionRepo;
    @InjectMocks
    SessionService sessionService;

    @Test
    void createNewSession_savesAndReturnsSession() {
        Session saved = new Session();
        saved.setSessionID(UUID.randomUUID());
        when(sessionRepo.save(any(Session.class))).thenReturn(saved);

        Session result = sessionService.createNewSession();
        assertThat(result.getSessionID()).isNotNull();
        verify(sessionRepo).save(any(Session.class));
    }

    @Test
    void getSessionMessages_returnsJson() {
        UUID id = UUID.randomUUID();
        Session s = new Session();
        s.setSessionID(id);
        s.setMessages("[]");
        when(sessionRepo.findById(id)).thenReturn(Optional.of(s));

        String json = sessionService.getSessionMessages(id);
        assertThat(json).isEqualTo("[]");
    }

    @Test
    void getSessionMessages_throwsWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(sessionRepo.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.getSessionMessages(id))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("Session not found");
    }
}
