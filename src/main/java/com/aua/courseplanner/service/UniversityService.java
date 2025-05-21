package com.aua.courseplanner.service;

import com.aua.courseplanner.entity.Course;
import com.aua.courseplanner.entity.Student;
import com.aua.courseplanner.repository.CourseRepository;
import com.aua.courseplanner.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UniversityService {
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public UniversityService(StudentRepository studentRepo, CourseRepository courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    /**
     * Get the list of courses available to the given student.
     * Exclude courses the student has already completed or expired and
     * filters out courses whose prerequisites are not yet satisfied.
     * Also fetch the schedule for each available course.
     */
    @Transactional(readOnly = true)
    public List<Course> getAvailableCoursesForStudent(Long studentId) {
        Student student = studentRepo.findByIdWithCourses(studentId)
            .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        Set<Long> completedCourseIds = student.getCourses()
            .stream()
            .map(Course::getCourseID)
            .collect(Collectors.toSet());

        return courseRepo.findAll()
            .stream()
            .filter(course ->
                !completedCourseIds.contains(course.getCourseID()) &&
                    (course.getPrerequisite() == null ||
                        completedCourseIds.contains(course.getPrerequisite().getCourseID())) &&
                    (course.getStartDate().after(new Date()))
            )
            .peek(course -> {
                if (course.getSchedule() != null) {
                    course.getSchedule().getWeekdays();
                }
            })
            .toList();
    }
}
