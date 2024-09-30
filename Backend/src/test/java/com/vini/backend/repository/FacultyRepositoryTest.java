package com.vini.backend.repository;

import com.vini.backend.models.Faculty;
import com.vini.backend.repositories.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FacultyRepositoryTest {

    @Autowired
    private FacultyRepository repository;

    @BeforeEach
    public void setUp() {
        Faculty faculty = new Faculty();
        faculty.setFacultyUid("faculty1");
        faculty.setFacultyEmail("t1@example.com");
        faculty.setFacultyName("Test Faculty");
        faculty.setFacultyPassword("password");
        faculty.setFacultyPhone("1234567890");
        faculty.setFacultyRole("ROLE_FACULTY");

        Faculty faculty1 = new Faculty();
        faculty1.setFacultyUid("faculty2");
        faculty1.setFacultyEmail("t2@example.com");
        faculty1.setFacultyName("Test Faculty 2");
        faculty1.setFacultyPassword("password");
        faculty1.setFacultyPhone("1234567890");
        faculty1.setFacultyRole("ROLE_FACULTY");

        repository.save(faculty1);
        repository.save(faculty);
    }

    @Test
    public void testFindByFacultyEmail() {
        Faculty faculty = repository.findByFacultyEmail("t1@example.com");
        assertNotNull(faculty);
    }

    @Test
    public void testFindAllFacultyByOrderByFacultyUid() {
        List<Faculty> facultyList = repository.findAllFacultyByOrderByFacultyUid();
        assertFalse(facultyList.isEmpty());
    }

    @Test
    public void testFindByFacultyUid() {
        Faculty faculty = repository.findByFacultyUid("faculty1");
        assertNotNull(faculty);
    }
}
