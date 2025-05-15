package com.aua.courseplanner.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Course")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseID;
    private String name;
    private int semester;
    private Date startDate;
    private Date endDate;
    private int credits;


    // Self-referential many-to-one relationship for prerequisite
    @ManyToOne
    @JoinColumn(name = "prerequisiteID")
    private Course prerequisite;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();
}