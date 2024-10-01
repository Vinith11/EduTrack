package com.vini.backend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vini.backend.controller.project.ProjectController;
import com.vini.backend.models.project.Project;
import com.vini.backend.service.project.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebMvcTest(controllers = ProjectController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateProject() throws Exception {
        Project project = new Project();
        project.setProjectId(150L);
        project.setStudentProjectName("AI Based System");
        project.setAcademicYear("2023");
        project.setStudentProjectLeaderId("2GI21CS022");
        project.setTeamMembers(List.of("2GI21CS023", "2GI21CS024"));
        project.setStudentProjectGuideId("103");
        project.setStudentProjectDomain("AI");
        project.setStudentProjectDescription("AI based project");
        project.setStudentProjectType("Research");
        project.setStudentProjectReport("https://project-report-url");
        project.setStudentProjectStart(LocalDate.parse("2024-01-01"));
        project.setStudentProjectCompletionStatus("Pending");
        project.setStudentProjectUrl("https://github.com/project-url");
        project.setFacultyApprovalStatus(false);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Register the module

        when(projectService.createProject(any(Project.class))).thenReturn(project);

        mockMvc.perform(post("/api/projects/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.project_id").value(150L))
                .andExpect(jsonPath("$.student_project_name").value("AI Based System"))
                .andExpect(jsonPath("$.academic_year").value("2023"))
                .andExpect(jsonPath("$.student_project_leader_id").value("2GI21CS022"))
                .andExpect(jsonPath("$.faculty_approval_status").value(false));
    }



    @Test
    public void testApproveProject() throws Exception {
        Project project = new Project();
        project.setProjectId(13L);
        project.setStudentProjectName("AI Based System");
        project.setAcademicYear("2023");
        project.setStudentProjectLeaderId("2GI21CS007");
        project.setTeamMembers(List.of("2GI21CS010", "2GI21CS011"));
        project.setStudentProjectGuideId("101");
        project.setStudentProjectDomain("AI");
        project.setStudentProjectDescription("AI based project");
        project.setStudentProjectType("Research");
        project.setStudentProjectReport("https://project-report-url");
        project.setStudentProjectStart(LocalDate.parse("2024-01-01"));
        project.setStudentProjectCompletionStatus("Pending");
        project.setStudentProjectUrl("https://github.com/project-url");
        project.setFacultyApprovalStatus(true);

        when(projectService.approveProject(13L, true)).thenReturn(project);

        mockMvc.perform(put("/api/projects/approve/{projectId}", 13L)
                        .param("approvalStatus", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.project_id").value(13L))
                .andExpect(jsonPath("$.student_project_name").value("AI Based System"))
                .andExpect(jsonPath("$.faculty_approval_status").value(true)); // Updated to snake_case
    }



    @Test
    public void testPendingApproveProjects() throws Exception {
        List<Project> projects = new ArrayList<>();

        Project project1 = new Project();
        project1.setProjectId(11L);
        project1.setStudentProjectName("AI Based System");
        project1.setAcademicYear("2023");
        project1.setStudentProjectLeaderId("2GI21CS007");
        project1.setTeamMembers(List.of("1RV17CS002", "1RV17CS003"));
        project1.setStudentProjectGuideId("101");
        project1.setStudentProjectDomain("AI");
        project1.setStudentProjectDescription("AI based project");
        project1.setStudentProjectType("Research");
        project1.setStudentProjectReport("https://project-report-url");
        project1.setStudentProjectStart(LocalDate.parse("2024-01-01"));
        project1.setStudentProjectCompletionStatus("Pending");
        project1.setStudentProjectUrl("https://github.com/project-url");
        project1.setFacultyApprovalStatus(true);

        Project project2 = new Project();
        project2.setProjectId(12L);
        project2.setStudentProjectName("AI Based System");
        project2.setAcademicYear("2023");
        project2.setStudentProjectLeaderId("2GI21CS007");
        project2.setTeamMembers(List.of("1RV17CS002", "1RV17CS003"));
        project2.setStudentProjectGuideId("101");
        project2.setStudentProjectDomain("AI");
        project2.setStudentProjectDescription("AI based project");
        project2.setStudentProjectType("Research");
        project2.setStudentProjectReport("https://project-report-url");
        project2.setStudentProjectStart(LocalDate.parse("2024-01-01"));
        project2.setStudentProjectCompletionStatus("Pending");
        project2.setStudentProjectUrl("https://github.com/project-url");
        project2.setFacultyApprovalStatus(true);

        projects.add(project1);
        projects.add(project2);

        when(projectService.pendingAproveProjects("facultyUid")).thenReturn(projects);

        mockMvc.perform(get("/api/projects/pending-projects/facultyUid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].project_id").value(11L))
                .andExpect(jsonPath("$[0].student_project_name").value("AI Based System"))
                .andExpect(jsonPath("$[0].academic_year").value("2023"))
                .andExpect(jsonPath("$[0].faculty_approval_status").value(true)) // Corrected to snake_case
                .andExpect(jsonPath("$[1].project_id").value(12L))
                .andExpect(jsonPath("$[1].faculty_approval_status").value(true)); // Corrected to snake_case
    }


    @Test
    public void testGetProjectsByBatch() throws Exception {
        List<Project> projects = new ArrayList<>();
        Project project = new Project();
        project.setProjectId(11L);
        project.setStudentProjectName("AI Based System");
        project.setAcademicYear("2023");
        project.setStudentProjectLeaderId("2GI21CS007");
        project.setTeamMembers(List.of("1RV17CS002", "1RV17CS003"));
        project.setStudentProjectGuideId("101");
        project.setStudentProjectDomain("AI");
        project.setStudentProjectDescription("AI based project");
        project.setStudentProjectType("Research");
        project.setStudentProjectReport("https://project-report-url");
        project.setStudentProjectStart(LocalDate.parse("2024-01-01"));
        project.setStudentProjectCompletionStatus("Pending");
        project.setStudentProjectUrl("https://github.com/project-url");
        project.setFacultyApprovalStatus(true);

        projects.add(project);

        when(projectService.getProjectsByBatch("2023")).thenReturn(projects);

        mockMvc.perform(get("/api/projects/2023"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].project_id").value(11L))
                .andExpect(jsonPath("$[0].student_project_name").value("AI Based System"))
                .andExpect(jsonPath("$[0].academic_year").value("2023"));
    }


    @Test
    public void testDeleteProject() throws Exception {
        doNothing().when(projectService).deleteProject(1L);

        mockMvc.perform(delete("/api/projects/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Project deleted successfully"));
    }
}
