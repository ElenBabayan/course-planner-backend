package com.aua.courseplanner.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Course")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseID;

    private String name;
    private int semester;
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "schedule")
    private Schedule schedule;

    private int credits;

    // Self-referential many-to-one relationship for prerequisite
    @ManyToOne
    @JoinColumn(name = "prerequisite_id")
    private Course prerequisite;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    public Course(Long courseID, String name, Date startDate, Date endDate, int credits, Course prerequisite) {
        this.courseID = courseID;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.credits = credits;
        this.prerequisite = prerequisite;
    }

    public Course() {
        // Default constructor
    }
}