package com.vini.backend.repository;

import com.vini.backend.models.internship.FacultyInternshipFeedback;
import com.vini.backend.repositories.FacultyInternshipFeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FacultyInternshipFeedbackRepositoryTest {

    @Autowired
    private FacultyInternshipFeedbackRepository repository;

    @BeforeEach
    public void setUp() {
        // Insert temporary data before each test
        FacultyInternshipFeedback feedback1 = new FacultyInternshipFeedback();
        feedback1.setFeedbackText("Great internship");
        feedback1.setFeedbackDate(LocalDate.now());
        feedback1.setInternshipId(1L);
        feedback1.setFacultyUid("faculty1");

        FacultyInternshipFeedback feedback2 = new FacultyInternshipFeedback();
        feedback2.setFeedbackText("Needs improvement");
        feedback2.setFeedbackDate(LocalDate.now());
        feedback2.setInternshipId(2L);
        feedback2.setFacultyUid("faculty2");

        repository.save(feedback1);
        repository.save(feedback2);
    }


    @Test
    public void testFindByFacultyUid() {
        List<FacultyInternshipFeedback> feedbacks = repository.findByFacultyUid("faculty1");
        assertEquals(1, feedbacks.size());
        assertEquals("Great internship", feedbacks.get(0).getFeedbackText());
    }

    @Test
    public void testFindByInternshipId() {
        FacultyInternshipFeedback feedback = repository.findByInternshipId(1L);
        assertNotNull(feedback);
        assertEquals("Great internship", feedback.getFeedbackText());
    }
}
