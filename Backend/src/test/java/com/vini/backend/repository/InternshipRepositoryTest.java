package com.vini.backend.repository;

import com.vini.backend.models.internship.Internship;
import com.vini.backend.repositories.InternshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class InternshipRepositoryTest {

    @Autowired
    private InternshipRepository repository;

    @BeforeEach
    public void setUp() {
        Internship internship = new Internship();
        internship.setStudentUsn("USN123");

        repository.save(internship);
    }

    @Test
    public void testFindByStudentUsn() {
        List<Internship> internships = repository.findByStudentUsn("USN123");
        assertFalse(internships.isEmpty());
    }
}
