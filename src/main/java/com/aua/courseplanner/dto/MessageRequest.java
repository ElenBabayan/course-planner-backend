package com.aua.courseplanner.dto;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;

@Getter
@Setter
public class MessageRequest {
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private List<Message> messages;
}