package com.aua.courseplanner.repository;

import com.aua.courseplanner.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Could define custom query for available courses, but handled in service for now
}