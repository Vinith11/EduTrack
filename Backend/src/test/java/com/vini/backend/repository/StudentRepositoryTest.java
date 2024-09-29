package com.vini.backend.repository;

import com.vini.backend.models.Student;
import com.vini.backend.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository repository;

    @BeforeEach
    public void setUp() {
        Student student = new Student();
        student.setStudentEmail("student@domain.com");
        student.setStudentName("Test Student");
        student.setStudentPassword("password");
        student.setStudentPhone("1234567890");
        student.setUsn("USN123");
        student.setProjectId(null);
        student.setStudentBatch("2024");
        student.setLeader(false);

        repository.save(student);
    }

    @Test
    public void testFindByStudentEmail() {
        Student student = repository.findByStudentEmail("student@domain.com");
        assertNotNull(student);
    }

    @Test
    public void testFindByUsn() {
        Student student = repository.findByUsn("USN123");
        assertNotNull(student);
    }
}
