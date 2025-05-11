package com.aua.courseplanner.repository;

import com.aua.courseplanner.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    /*
    Without it: student.getCourses() fails outside a Hibernate session.
    With it: courses are loaded immediately with the student — safe to use in our service or controller.
     */
    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.studentID = :id")
    Optional<Student> findByIdWithCourses(@Param("id") Long id);

}