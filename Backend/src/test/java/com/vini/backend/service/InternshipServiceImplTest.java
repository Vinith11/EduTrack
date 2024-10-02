package com.vini.backend.service;

import com.vini.backend.exception.NotFoundException;
import com.vini.backend.models.Faculty;
import com.vini.backend.models.Student;
import com.vini.backend.models.internship.Internship;
import com.vini.backend.repositories.FacultyRepository;
import com.vini.backend.repositories.InternshipRepository;
import com.vini.backend.repositories.StudentRepository;
import com.vini.backend.service.internship.InternshipServiceImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InternshipServiceImplTest {

    @Mock
    private InternshipRepository internshipRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private InternshipServiceImpl internshipService;

    private Internship internship;
    private Student student;
    private Faculty faculty;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize sample internship, student, and faculty
        internship = new Internship();
        internship.setInternshipId(4L);
        internship.setStudentUsn("USN123");
        internship.setFacultyUid("FACULTY123");

        student = new Student();
        student.setUsn("USN123");

        faculty = new Faculty();
        faculty.setFacultyUid("FACULTY123");
    }

    @Test
    public void testGetAllInternships() {
        // Arrange
        List<Internship> internshipList = Arrays.asList(internship);
        when(internshipRepository.findAll()).thenReturn(internshipList);

        // Act
        List<Internship> result = internshipService.getAllInternships();

        // Assert
        assertEquals(1, result.size());
        verify(internshipRepository, times(1)).findAll();
    }

    @Test
    public void testGetInternshipById_ExistingId() throws NotFoundException {
        // Arrange
        when(internshipRepository.findById(4L)).thenReturn(Optional.of(internship));

        // Act
        Optional<Internship> result = internshipService.getInternshipById(4L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(internship.getInternshipId(), result.get().getInternshipId());
        verify(internshipRepository, times(2)).findById(4L);
    }

    @Test
    public void testGetInternshipById_NotFound() {
        // Arrange
        when(internshipRepository.findById(4L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            internshipService.getInternshipById(4L);
        });
    }

    @Test
    public void testCreateInternship_Success() throws NotFoundException {
        // Arrange
        when(studentRepository.findById("USN123")).thenReturn(Optional.of(student));
        when(facultyRepository.findByFacultyUid("FACULTY123")).thenReturn(faculty);
        when(internshipRepository.save(internship)).thenReturn(internship);

        // Act
        Internship result = internshipService.createInternship(internship);

        // Assert
        assertNotNull(result);
        assertEquals(internship.getInternshipId(), result.getInternshipId());
        verify(internshipRepository, times(1)).save(internship);
    }

    @Test
    public void testCreateInternship_StudentNotFound() {
        // Arrange
        when(studentRepository.findById("USN123")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            internshipService.createInternship(internship);
        });
    }

    @Test
    public void testCreateInternship_FacultyNotFound() {
        // Arrange
        when(studentRepository.findById("USN123")).thenReturn(Optional.of(student));
        when(facultyRepository.findByFacultyUid("FACULTY123")).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            internshipService.createInternship(internship);
        });
    }

    @Test
    public void testUpdateInternship_Success() throws NotFoundException {
        // Arrange
        when(studentRepository.findById("USN123")).thenReturn(Optional.of(student));
        when(facultyRepository.findByFacultyUid("FACULTY123")).thenReturn(faculty);
        when(internshipRepository.findById(4L)).thenReturn(Optional.of(internship));
        when(internshipRepository.save(any(Internship.class))).thenReturn(internship);

        // Act
        Internship result = internshipService.updateInternship(4L, internship);

        // Assert
        assertNotNull(result);
        assertEquals(internship.getInternshipId(), result.getInternshipId());
        verify(internshipRepository, times(1)).save(any(Internship.class));
    }

    @Test
    public void testUpdateInternship_NotFound() {
        // Arrange
        lenient().when(studentRepository.findById("USN123")).thenReturn(Optional.of(student));
        lenient().when(facultyRepository.findByFacultyUid("FACULTY123")).thenReturn(faculty);
        when(internshipRepository.findById(4L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            internshipService.updateInternship(4L, internship);
        });
    }



    @Test
    public void testDeleteInternship_Success() throws NotFoundException {
        // Arrange
        when(internshipRepository.findById(4L)).thenReturn(Optional.of(internship));

        // Act
        internshipService.deleteInternship(4L);

        // Assert
        verify(internshipRepository, times(1)).deleteById(4L);
    }

    @Test
    public void testDeleteInternship_NotFound() {
        // Arrange
        when(internshipRepository.findById(4L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            internshipService.deleteInternship(4L);
        });
    }

    @Test
    public void testGetInternshipsByStudentUsn_Success() throws NotFoundException {
        // Arrange
        when(studentRepository.findById("USN123")).thenReturn(Optional.of(student));
        when(internshipRepository.findByStudentUsn("USN123")).thenReturn(Arrays.asList(internship));

        // Act
        List<Internship> result = internshipService.getInternshipsByStudentUsn("USN123");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(internshipRepository, times(2)).findByStudentUsn("USN123");
    }

    @Test
    public void testGetInternshipsByStudentUsn_NotFound() {
        // Arrange
        when(studentRepository.findById("USN123")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            internshipService.getInternshipsByStudentUsn("USN123");
        });
    }
}

