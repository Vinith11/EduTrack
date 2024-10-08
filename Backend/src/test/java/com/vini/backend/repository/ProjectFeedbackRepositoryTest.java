package com.vini.backend.repository;

import com.vini.backend.models.project.ProjectFeedback;
import com.vini.backend.repositories.ProjectFeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProjectFeedbackRepositoryTest {

    @Autowired
    private ProjectFeedbackRepository repository;

    @BeforeEach
    public void setUp() {
        ProjectFeedback feedback = new ProjectFeedback();
        feedback.setProjectId(1L);
        feedback.setFeedbackText("Good");
        repository.save(feedback);
    }

    @Test
    public void testFindById() {
        Optional<ProjectFeedback> feedback = repository.findById(1L);
        assertTrue(feedback.isPresent());
    }
}
