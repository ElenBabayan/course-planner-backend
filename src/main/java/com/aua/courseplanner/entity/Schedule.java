package com.aua.courseplanner.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalTime;
import java.util.Set;


@Entity
@Table(name = "Schedule")
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startsAt;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endsAt;

    public enum DayOfWeek{MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY}

    @ElementCollection(targetClass = DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @Column(name = "day_of_week")
    private Set<DayOfWeek> daysOfWeek;
}