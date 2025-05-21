package com.aua.courseplanner.entity;

import com.aua.courseplanner.dto.MessageRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "Session")
@Data
public class Session {
    @Id
    @Column(name = "session_id")
    private UUID sessionID;

    // Use a JSON column to store the conversation
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private MessageRequest messages;

    public Session() {
        this.sessionID = UUID.randomUUID();
        this.messages = new MessageRequest();
    }
}