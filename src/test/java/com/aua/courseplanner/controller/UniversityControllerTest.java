package com.aua.courseplanner.controller;

import com.aua.courseplanner.entity.Course;
import com.aua.courseplanner.service.UniversityService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UniversityController.class)
class UniversityControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    UniversityService universityService;

    @Test
    void returnsEmptyArray_whenNoCourses() throws Exception {
        when(universityService.getAvailableCoursesForStudent(1L))
                .thenReturn(List.of());

        mvc.perform(get("/university/availableCourses/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void returnsSingleCourse() throws Exception {
        Course algo = new Course(103L, "Algorithms", null, null, 4, null);
        when(universityService.getAvailableCoursesForStudent(1L))
                .thenReturn(List.of(algo));

        mvc.perform(get("/university/availableCourses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Algorithms"));
    }

    @Test
    void returnsMultipleCourses() throws Exception {
        Course algo = new Course(103L, "Algorithms", null, null, 4, null);
        Course english = new Course(111L, "English I", null, null, 3, null);
        when(universityService.getAvailableCoursesForStudent(1L))
                .thenReturn(List.of(algo, english));

        mvc.perform(get("/university/availableCourses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[?(@.courseID==103)].name").value("Algorithms"));
    }

    @Test
    void returns404_whenStudentNotFound() throws Exception {
        when(universityService.getAvailableCoursesForStudent(99L))
                .thenThrow(new EntityNotFoundException("Student not found"));

        mvc.perform(get("/university/availableCourses/99"))
                .andExpect(status().isNotFound());
    }
}
