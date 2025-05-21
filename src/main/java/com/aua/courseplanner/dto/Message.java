package com.aua.courseplanner.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Message {
    private String role;
    private String content;
    private List<String> parts;
}