package com.vini.backend.service.implementaion;

import com.vini.backend.models.Faculty;
import com.vini.backend.models.Project;
import com.vini.backend.models.Student;
import com.vini.backend.repositories.FacultyRepository;
import com.vini.backend.repositories.ProjectRepository;
import com.vini.backend.repositories.StudentRepository;
import com.vini.backend.service.EmailService;
import com.vini.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private EmailService emailService; // Assume EmailService is responsible for sending emails

    @Override
    public Project createProjectGroup(Project projectRequest, List<String> studentIds) throws Exception {
        if (studentIds.size() < 2 || studentIds.size() > 4) {
            throw new Exception("Team must have 2-4 members including the leader.");
        }



        // Set the team members
        projectRequest.setTeamMembers(studentIds);
        projectRequest.setFacultyApprovalStatus(false);  // Initially not approved

        // Save the project in DB (initially without faculty approval)
        Project savedProject = projectRepository.save(projectRequest);

        // Send email to faculty
        Faculty faculty = facultyRepository.findById(projectRequest.getStudentProjectGuideId())
                .orElseThrow(() -> new Exception("Faculty not found"));

        emailService.sendApprovalRequestEmail(faculty.getFacultyEmail(), savedProject);

        return savedProject;
    }

    @Override
    public void updateFacultyApproval(Long projectId, boolean isApproved) throws Exception{
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Exception("Project not found"));
        Student student = studentRepository.findByUsn(project.getStudentProjectLeaderId());

        // Notify students if approved
        if (isApproved) {
            // Additional notification or update logic can be added here
            project.setFacultyApprovalStatus(isApproved);
            projectRepository.save(project);
            emailService.sendAcceptEmail(student.getStudentEmail(), project);
        } else{
            emailService.sendRejectionEmail(student.getStudentEmail(), project);
            projectRepository.delete(project);

        }
    }

    @Override
    public void completeProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setStudentProjectCompletionStatus("COMPLETED");
        projectRepository.save(project);
    }
}
