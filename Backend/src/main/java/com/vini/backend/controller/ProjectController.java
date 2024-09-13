package com.vini.backend.controller;

import com.vini.backend.dto.ProjectRequestDTO;
import com.vini.backend.models.Project;
import com.vini.backend.models.Student;
import com.vini.backend.response.ApiResponse;
import com.vini.backend.response.AuthResponse;
import com.vini.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Endpoint to create a project group
    @PostMapping("/create")
    public ResponseEntity<Project> createProjectGroup(@RequestBody ProjectRequestDTO projectRequestDTO) {
        try {
            Project createdProject = projectService.createProjectGroup(
                    projectRequestDTO.getProject(), projectRequestDTO.getTeamMembers());
            return ResponseEntity.ok(createdProject);
        } catch (Exception e) {
            System.out.println(projectRequestDTO.getProject());
            System.out.println(projectRequestDTO.getTeamMembers());
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint to update faculty approval
    @PostMapping("/{projectId}/approve")
    public ResponseEntity<ApiResponse> updateFacultyApproval(@PathVariable Long projectId, @RequestParam boolean isApproved) throws Exception {
        projectService.updateFacultyApproval(projectId, isApproved);
        ApiResponse res = new ApiResponse();
        res.setMessage("Faculty approval updated");
        res.setStatus(true);
        return ResponseEntity.ok(res);
    }

    // Endpoint to mark the project as complete
    @PostMapping("/{projectId}/complete")
    public ResponseEntity<ApiResponse> completeProject(@PathVariable Long projectId) {
        projectService.completeProject(projectId);
        ApiResponse res = new ApiResponse();
        res.setMessage("Project marked as completed");
        res.setStatus(true);
        return ResponseEntity.ok(res);
    }
}
