package com.aua.courseplanner.controller;

import com.aua.courseplanner.entity.Course;
import com.aua.courseplanner.service.UniversityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/university")
public class UniversityController {
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    /**
     * GET /university/availableCourses/{studentID}
     * Returns courses available to the student (not yet taken and prereqs satisfied).
     */
    @GetMapping("/availableCourses/{studentID}")
    public ResponseEntity<List<Course>> getAvailableCourses(@PathVariable Long studentID) {
        List<Course> available = universityService.getAvailableCoursesForStudent(studentID);
        return ResponseEntity.ok(available);
    }
}
