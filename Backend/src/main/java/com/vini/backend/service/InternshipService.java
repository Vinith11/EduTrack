package com.vini.backend.service;

import com.vini.backend.exception.UserException;
import com.vini.backend.models.Internship;

import java.util.List;

public interface InternshipService {
    Internship saveInternship(Internship internship);
    Internship updateInternship(Long id, Internship internship);
    void deleteInternship(Long id);
    Internship getInternshipById(Long id);
    List<Internship> getAllInternships();

}
