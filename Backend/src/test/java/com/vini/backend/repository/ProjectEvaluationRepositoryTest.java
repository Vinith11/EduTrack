package com.vini.backend.repository;

import com.vini.backend.models.project.ProjectEvaluation;
import com.vini.backend.repositories.ProjectEvaluationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProjectEvaluationRepositoryTest {

    @Autowired
    private ProjectEvaluationRepository repository;

    @BeforeEach
    public void setUp() {
        ProjectEvaluation evaluation = new ProjectEvaluation();
        evaluation.setProjectId(1L);
        evaluation.setFacultyUid("faculty1");
        repository.save(evaluation);
    }

    @Test
    public void testExistsByProjectIdAndFacultyUid() {
        boolean exists = repository.existsByProjectIdAndFacultyUid(1L, "faculty1");
        assertTrue(exists);
    }

    @Test
    public void testFindByProjectId() {
        ProjectEvaluation evaluation = repository.findByProjectId(1L);
        assertNotNull(evaluation);
    }

    @Test
    public void testFindEvaluationsByFacultyAndBatch() {
        List<ProjectEvaluation> evaluations = repository.findEvaluationsByFacultyAndBatch("faculty1", "2024");
        assertTrue(evaluations.isEmpty());
    }

}
