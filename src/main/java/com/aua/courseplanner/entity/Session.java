package com.aua.courseplanner.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.*;
import jakarta.persistence.Table;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "Session")
@Data
public class Session {
    @Id
    private UUID sessionID;   // Use UUID for unique session identifiers

    // Use a JSON column to store the conversation
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String messages;

    public Session() {
        this.sessionID = UUID.randomUUID();
        this.messages = "[]";  // default to empty JSON array
    }

    public Session(String initialMessagesJson) {
        this.sessionID = UUID.randomUUID();
        this.messages = initialMessagesJson;
    }
}