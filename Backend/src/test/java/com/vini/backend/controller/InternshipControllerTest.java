package com.vini.backend.controller;


import com.vini.backend.controller.internship.InternshipController;
import com.vini.backend.models.internship.Internship;
import com.vini.backend.service.internship.InternshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = InternshipController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class InternshipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InternshipService internshipService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllInternships() throws Exception {
        List<Internship> internships = new ArrayList<>();
        when(internshipService.getAllInternships()).thenReturn(internships);

        mockMvc.perform(get("/api/internships/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(internshipService, times(1)).getAllInternships();
    }

    @Test
    public void testGetInternshipById_Success() throws Exception {
        Internship internship = new Internship();
        when(internshipService.getInternshipById(1L)).thenReturn(Optional.of(internship));

        mockMvc.perform(get("/api/internships/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(internshipService, times(1)).getInternshipById(1L);
    }

    @Test
    public void testGetInternshipById_NotFound() throws Exception {
        when(internshipService.getInternshipById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/internships/1"))
                .andExpect(status().isNotFound());

        verify(internshipService, times(1)).getInternshipById(1L);
    }

    @Test
    public void testCreateInternship() throws Exception {
        Internship internship = new Internship();
        when(internshipService.createInternship(any(Internship.class))).thenReturn(internship);

        mockMvc.perform(post("/api/internships/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Internship\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(internshipService, times(1)).createInternship(any(Internship.class));
    }

    @Test
    public void testUpdateInternship_Success() throws Exception {
        Internship internship = new Internship();
        when(internshipService.updateInternship(eq(1L), any(Internship.class))).thenReturn(internship);

        mockMvc.perform(put("/api/internships/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Internship\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(internshipService, times(1)).updateInternship(eq(1L), any(Internship.class));
    }

    @Test
    public void testUpdateInternship_NotFound() throws Exception {
        when(internshipService.updateInternship(eq(1L), any(Internship.class))).thenReturn(null);

        mockMvc.perform(put("/api/internships/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Internship\"}"))
                .andExpect(status().isNotFound());

        verify(internshipService, times(1)).updateInternship(eq(1L), any(Internship.class));
    }

    @Test
    public void testDeleteInternship_Success() throws Exception {
        Internship internship = new Internship();
        when(internshipService.getInternshipById(1L)).thenReturn(Optional.of(internship));
        doNothing().when(internshipService).deleteInternship(1L);

        mockMvc.perform(delete("/api/internships/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Internship Deleted Successfully"));

        verify(internshipService, times(1)).deleteInternship(1L);
    }

    @Test
    public void testGetInternshipsByStudentUsn() throws Exception {
        List<Internship> internships = new ArrayList<>();
        when(internshipService.getInternshipsByStudentUsn("1RV20CS001")).thenReturn(internships);

        mockMvc.perform(get("/api/internships/student/1RV20CS001"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(internshipService, times(1)).getInternshipsByStudentUsn("1RV20CS001");
    }
}

