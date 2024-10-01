package com.vini.backend.repository;

import com.vini.backend.models.internship.InternshipEvaluation;
import com.vini.backend.repositories.InternshipEvaluationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class InternshipEvaluationRepositoryTest {

    @Autowired
    private InternshipEvaluationRepository repository;

    @BeforeEach
    public void setUp() {
        InternshipEvaluation evaluation = new InternshipEvaluation();
        evaluation.setInternshipId(1L);
        evaluation.setFacultyUid("faculty1");
        repository.save(evaluation);
    }

    @Test
    public void testFindByInternshipId() {
        Optional<InternshipEvaluation> evaluation = repository.findByInternshipId(1L);
        assertTrue(evaluation.isPresent());
    }

    @Test
    public void testFindByFacultyUid() {
        List<InternshipEvaluation> evaluations = repository.findByFacultyUid("faculty1");
        assertFalse(evaluations.isEmpty());
    }
}
