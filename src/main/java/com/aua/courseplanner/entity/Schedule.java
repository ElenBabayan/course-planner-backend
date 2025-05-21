package com.aua.courseplanner.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleID;

    @Column(name = "starts_at", nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startsAt;

    @Column(name = "ends_at", nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endsAt;

    @Column(name = "weekdays")
    private String weekdays;
}
