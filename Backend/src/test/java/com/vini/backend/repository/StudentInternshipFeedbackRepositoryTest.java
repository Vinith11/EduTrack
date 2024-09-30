package com.vini.backend.repository;

import com.vini.backend.models.internship.StudentInternshipFeedback;
import com.vini.backend.repositories.StudentInternshipFeedbackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StudentInternshipFeedbackRepositoryTest {

    @Autowired
    private StudentInternshipFeedbackRepository repository;

    @BeforeEach
    public void setUp() {
        StudentInternshipFeedback feedback = new StudentInternshipFeedback();
        feedback.setInternshipId(1L);
        feedback.setUsn("USN123");
        feedback.setFeedbackText("Good");
        feedback.setFeedbackDate(LocalDate.parse("2021-08-01"));
        feedback.setInternshipId(1L);
        repository.save(feedback);
    }

    @Test
    public void testFindByInternshipId() {
        StudentInternshipFeedback feedback = repository.findByInternshipId(1L);
        assertNotNull(feedback);
    }

    @Test
    public void testFindByUsn() {
        List<StudentInternshipFeedback> feedbackList = repository.findByUsn("USN123");
        assertFalse(feedbackList.isEmpty());
    }
}
