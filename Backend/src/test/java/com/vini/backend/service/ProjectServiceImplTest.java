package com.vini.backend.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



import com.vini.backend.exception.NotFoundException;
import com.vini.backend.models.Faculty;
import com.vini.backend.models.Student;
import com.vini.backend.models.project.FacultyProjectGuide;
import com.vini.backend.models.project.Project;
import com.vini.backend.repositories.FacultyProjectGuideRepository;
import com.vini.backend.repositories.FacultyRepository;
import com.vini.backend.repositories.ProjectRepository;
import com.vini.backend.repositories.StudentRepository;
import com.vini.backend.service.email.EmailServiceImpl;
import com.vini.backend.service.project.ProjectServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @Mock
    private FacultyProjectGuideRepository facultyProjectGuideRepository;

    @MockBean
    private EmailServiceImpl emailService;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Mock
    private ProjectRepository actualProjectRepository;

    @Mock
    private StudentRepository actualStudentRepository;

    @Mock
    private FacultyRepository actualFacultyRepository;

    @Mock
    private FacultyProjectGuideRepository actualFacultyProjectGuideRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        insertDataIntoDatabase();
    }

    private void insertDataIntoDatabase() {
        // Insert Faculty Data
        Faculty faculty1 = new Faculty();
        faculty1.setFacultyUid("FAC001");
        faculty1.setFacultyName("Dr. Smith");
        faculty1.setFacultyPhone("1234567890");
        faculty1.setFacultyEmail("dr.smith@university.com");
        faculty1.setFacultyRole("Guide");
        faculty1.setFacultyPassword("password");
        actualFacultyRepository.save(faculty1);

        // Insert Student Data
        Student student1 = new Student();
        student1.setUsn("STU001");
        student1.setStudentName("John Doe");
        student1.setStudentPhone("9876543210");
        student1.setStudentEmail("john.doe@university.com");
        student1.setStudentBatch("2023");
        student1.setStudentPassword("password");
        student1.setLeader(false);
        actualStudentRepository.save(student1);

        Student student2 = new Student();
        student2.setUsn("STU002");
        student2.setStudentName("Jane Roe");
        student2.setStudentPhone("1231231234");
        student2.setStudentEmail("jane.roe@university.com");
        student2.setStudentBatch("2023");
        student2.setStudentPassword("password");
        student2.setLeader(false);
        actualStudentRepository.save(student2);

        Student student3 = new Student();
        student3.setUsn("STU003");
        student3.setStudentName("Alice");
        student3.setStudentPhone("1231231234");
        student3.setStudentEmail("alice.bing@uni,com");
        student3.setStudentBatch("2023");
        student3.setStudentPassword("password");
        student3.setLeader(false);
        actualStudentRepository.save(student3);


        // Insert Project Data
        Project project1 = new Project();
        project1.setStudentProjectName("AI Research");
        project1.setAcademicYear("2023");
        project1.setStudentProjectLeaderId("STU001");
        project1.setTeamMembers(List.of("STU002"));
        project1.setStudentProjectGuideId("FAC001");
        project1.setStudentProjectDomain("AI");
        project1.setStudentProjectDescription("Research on AI advancements.");
        project1.setStudentProjectType("Research");
        project1.setFacultyApprovalStatus(false);
        actualProjectRepository.save(project1);

        // Insert FacultyProjectGuide Data
        FacultyProjectGuide guide = new FacultyProjectGuide();
        guide.setProjectId(project1.getProjectId());
        guide.setFacultyUid("FAC001");
        guide.setBatch("2023");
        actualFacultyProjectGuideRepository.save(guide);
    }

    @Test
    public void testCreateProject_LeaderNotFound() {
        Project project = new Project();
        project.setStudentProjectLeaderId("leader123");

        when(studentRepository.findById("leader123")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            projectService.createProject(project);
        });

        assertEquals("Project leader not found.", exception.getMessage());
    }

    @Test
    public void testCreateProject_FacultyNotFound() {
        Project project = new Project();
        project.setStudentProjectLeaderId("STU001");
        project.setStudentProjectGuideId("FAC999"); // Non-existing faculty UID

        when(studentRepository.findById("STU001")).thenReturn(Optional.of(new Student()));
        when(facultyRepository.findById("FAC999")).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            projectService.createProject(project);
        });

        assertEquals("Faculty guide not found.", exception.getMessage());
    }



    @Test
    public void testCreateProject_LeaderAlreadyHasProject() {
        Project project = new Project();
        project.setStudentProjectLeaderId("STU001");
        project.setStudentProjectGuideId("FAC001"); // Valid faculty ID

        // Mocking the student repository to return a valid student
        when(studentRepository.findById("STU001")).thenReturn(Optional.of(new Student()));

        // Mocking the faculty repository to return a valid faculty
        when(facultyRepository.findById("FAC001")).thenReturn(Optional.of(new Faculty()));

        // Mocking the project repository to simulate that the leader already has a project
        when(projectRepository.existsByStudentProjectLeaderId("STU001")).thenReturn(true);

        // Asserting that the correct exception is thrown
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            projectService.createProject(project);
        });

        assertEquals("Student with USN STU001 already has a project.", exception.getMessage());
    }

    @Test
    public void testCreateProject_TeamMemberAlreadyHasProject() {
        // Setup project with leader and team members
        Project project = new Project();
        project.setStudentProjectLeaderId("STU001");
        project.setStudentProjectGuideId("FAC001");
        project.setTeamMembers(Arrays.asList("STU002", "STU003"));

        // Mock the project leader (STU001) existence
        Student leader = new Student();
        leader.setUsn("STU001");
        when(studentRepository.findById("STU001")).thenReturn(Optional.of(leader));

        // Mock faculty existence
        Faculty faculty = new Faculty();
        when(facultyRepository.findById("FAC001")).thenReturn(Optional.of(faculty));

        // Mock the team member who already has a project (STU002)
        Student teamMemberWithProject = new Student();
        teamMemberWithProject.setUsn("STU002");
        teamMemberWithProject.setProjectId(1L);  // Team member already has a project
        when(studentRepository.findById("STU002")).thenReturn(Optional.of(teamMemberWithProject));

        // No need to mock STU003 since the logic fails with STU002

        // Expect that an exception is thrown due to STU002 already being part of another project
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            projectService.createProject(project);
        });

        // Verify that the exception message is correct
        assertEquals("Student with USN STU002 is already part of another group.", exception.getMessage());

        // Verify that the projectRepository save method was not called (since the project creation failed)
        verify(projectRepository, never()).save(any(Project.class));
    }



    @Test
    public void testApproveProject_AlreadyApproved() {
        Project project = new Project();
        project.setProjectId(1L);
        project.setFacultyApprovalStatus(true); // Already approved

        // Mocking the project repository to return an existing project
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        // Assert that the exception is thrown
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            projectService.approveProject(1L, true);
        });

        assertEquals("Project already approved.", exception.getMessage());
    }







    @Test
    public void testDeleteProject_Success() throws NotFoundException {
        Project project = new Project();
        project.setProjectId(1L);
        project.setStudentProjectLeaderId("STU001");
        project.setTeamMembers(Arrays.asList("STU002", "STU003"));

        // Mocking the project repository to return the project to be deleted
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        // Mocking the student repository to return the project leader and members
        when(studentRepository.findById("STU001")).thenReturn(Optional.of(new Student()));
        when(studentRepository.findById("STU002")).thenReturn(Optional.of(new Student()));
        when(studentRepository.findById("STU003")).thenReturn(Optional.of(new Student()));

        projectService.deleteProject(1L);

        verify(projectRepository, times(1)).delete(project); // Verify project deletion
        verify(studentRepository, times(3)).save(any(Student.class)); // Verify student updates
    }





}
