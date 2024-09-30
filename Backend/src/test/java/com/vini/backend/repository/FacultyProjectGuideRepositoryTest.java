package com.vini.backend.repository;

import com.vini.backend.repositories.FacultyProjectGuideRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FacultyProjectGuideRepositoryTest {

    @Autowired
    private FacultyProjectGuideRepository repository;

    @BeforeEach
    public void setUp() {
    }


    @Test
    public void testCountByFacultyUidAndBatch() {
        Long count = repository.countByFacultyUidAndBatch("faculty2", "2024");
        assertEquals(0L, count);
    }
}
