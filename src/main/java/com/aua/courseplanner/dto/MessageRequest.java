package com.aua.courseplanner.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageRequest {
    private List<Message> messages;
}