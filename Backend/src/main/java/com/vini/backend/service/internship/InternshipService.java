package com.vini.backend.service.internship;

import com.vini.backend.exception.InternshipNotFoundException;
import com.vini.backend.exception.StudentNotFoundException;
import com.vini.backend.models.internship.Internship;

import java.util.List;
import java.util.Optional;

public interface InternshipService {
    List<Internship> getAllInternships();
    Optional<Internship> getInternshipById(Long internshipId) throws InternshipNotFoundException;
    Internship createInternship(Internship internship) ;
    Internship updateInternship(Long internshipId, Internship internship) throws InternshipNotFoundException;
    void deleteInternship(Long internshipId) throws InternshipNotFoundException;
    List<Internship> getInternshipsByStudentUsn(String studentUsn) throws StudentNotFoundException;
}
