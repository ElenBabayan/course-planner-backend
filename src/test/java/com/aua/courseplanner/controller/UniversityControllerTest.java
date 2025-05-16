package com.aua.courseplanner.controller;

import com.aua.courseplanner.entity.Course;
import com.aua.courseplanner.service.UniversityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UniversityController.class)
class UniversityControllerTest {
    @Autowired MockMvc mvc;

    @MockBean UniversityService universityService;

    @Test
    void returnsAvailableCourses() throws Exception {
        when(universityService.getAvailableCoursesForStudent(1L))
                .thenReturn(List.of(new Course(1L, "Algorithms", null, null, 4, null)));

        mvc.perform(get("/university/availableCourses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Algorithms"));
    }
}
