package com.aua.courseplanner.service;

import com.aua.courseplanner.entity.Course;
import com.aua.courseplanner.entity.Student;
import com.aua.courseplanner.repository.CourseRepository;
import com.aua.courseplanner.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UniversityServiceTest {
    @Mock
    StudentRepository studentRepo;
    @Mock
    CourseRepository courseRepo;

    @InjectMocks
    UniversityService universityService;

    Course ds, algo, englishI, englishII;
    Student student;

    @BeforeEach
    void setUp() {
        ds = new Course(102L, "Data Structures", null, null, 3, null);
        algo = new Course(103L, "Algorithms", null, null, 4, ds);
        englishI = new Course(111L, "English Literature I", null, null, 3, null);
        englishII = new Course(105L, "English Literature II", null, null, 3, englishI);

        student = new Student();
        student.setStudentID(1L);
        student.setCourses(Set.of(ds));
    }

    @Test
    void returnsAvailableCourses_whenPrereqsMet() {
        when(studentRepo.findByIdWithCourses(1L)).thenReturn(Optional.of(student));
        when(courseRepo.findAll()).thenReturn(List.of(ds, algo, englishI, englishII));

        List<Course> result = universityService.getAvailableCoursesForStudent(1L);

        assertThat(result)
                .extracting(Course::getCourseID)
                .containsExactlyInAnyOrder(103L, 111L) // Algorithms & English I
                .doesNotContain(102L, 105L);          // already taken & prereq not met
    }

    @Test
    void returnsEmptyList_whenNoCoursesAvailable() {
        when(studentRepo.findByIdWithCourses(1L)).thenReturn(Optional.of(student));
        // All repo courses are already completed by student
        when(courseRepo.findAll()).thenReturn(List.of(ds));

        List<Course> result = universityService.getAvailableCoursesForStudent(1L);
        assertThat(result).isEmpty();
    }

    @Test
    void throwsWhenStudentNotFound() {
        when(studentRepo.findByIdWithCourses(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> universityService.getAvailableCoursesForStudent(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Student not found");
    }
}