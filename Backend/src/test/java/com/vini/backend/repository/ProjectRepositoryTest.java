package com.vini.backend.repository;

import com.vini.backend.models.project.Project;
import com.vini.backend.repositories.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository repository;

    @BeforeEach
    public void setUp() {
        Project project = new Project();
        project.setStudentProjectDescription("Project1");
        project.setStudentProjectLeaderId("student1");
        project.setStudentProjectGuideId("faculty1");
        project.setAcademicYear("2024");
        project.setFacultyApprovalStatus(true);
        repository.save(project);
    }

    @Test
    public void testFindByStudentProjectGuideIdAndFacultyApprovalStatus() {
        List<Project> projects = repository.findByStudentProjectGuideIdAndFacultyApprovalStatus("faculty1", true);
        assertFalse(projects.isEmpty());
    }

    @Test
    public void testExistsByStudentProjectLeaderId() {
        boolean exists = repository.existsByStudentProjectLeaderId("student1");
        assertTrue(exists);
    }
}

