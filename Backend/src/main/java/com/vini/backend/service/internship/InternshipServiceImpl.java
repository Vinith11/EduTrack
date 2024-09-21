package com.vini.backend.service.internship;

import com.vini.backend.exception.InternshipNotFoundException;
import com.vini.backend.exception.StudentNotFoundException;
import com.vini.backend.models.internship.Internship;
import com.vini.backend.repositories.InternshipRepository;
import com.vini.backend.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternshipServiceImpl implements InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }

    @Override
    public Optional<Internship> getInternshipById(Long internshipId) throws InternshipNotFoundException {
        Optional<Internship> existingInternship = internshipRepository.findById(internshipId);
        if (existingInternship.isEmpty()) {
            throw new InternshipNotFoundException("Internship not found with ID " + internshipId);
        }
        return internshipRepository.findById(internshipId);
    }

    @Override
    public Internship createInternship(Internship internship) {
        return internshipRepository.save(internship);
    }

    @Override
    public Internship updateInternship(Long internshipId, Internship internship) throws InternshipNotFoundException {
        Optional<Internship> existingInternship = internshipRepository.findById(internshipId);
        if (existingInternship.isPresent()) {
            Internship updatedInternship = existingInternship.get();
            updatedInternship.setStudentUsn(internship.getStudentUsn());
            updatedInternship.setInternshipStart(internship.getInternshipStart());
            updatedInternship.setInternshipEnd(internship.getInternshipEnd());
            updatedInternship.setInternshipDuration(internship.getInternshipDuration());
            updatedInternship.setInternshipCertificate(internship.getInternshipCertificate());
            updatedInternship.setInternshipLocation(internship.getInternshipLocation());
            updatedInternship.setInternshipDomain(internship.getInternshipDomain());
            updatedInternship.setInternshipEvaluationSheet(internship.getInternshipEvaluationSheet());
            updatedInternship.setInternshipCompletionCertificateUrl(internship.getInternshipCompletionCertificateUrl());
            updatedInternship.setFacultyUid(internship.getFacultyUid());
            return internshipRepository.save(updatedInternship);
        }
        throw new InternshipNotFoundException("Internship not found with ID " + internshipId);
    }

    @Override
    public void deleteInternship(Long internshipId) throws InternshipNotFoundException {
        Optional<Internship> existingInternship = internshipRepository.findById(internshipId);
        if (existingInternship.isEmpty()) {
            throw new InternshipNotFoundException("Internship not found with ID " + internshipId);
        }
        internshipRepository.deleteById(internshipId);
    }

    // Implementing the new method to fetch internships by student USN
    @Override
    public List<Internship> getInternshipsByStudentUsn(String studentUsn) throws StudentNotFoundException {
            // Fetch the student by USN
            studentRepository.findById(studentUsn)
                    .orElseThrow(() -> new StudentNotFoundException("Student not found with USN " + studentUsn));
        if(internshipRepository.findByStudentUsn(studentUsn).isEmpty()) {
            throw new StudentNotFoundException("Internship not found with USN " + studentUsn);
        }

        return internshipRepository.findByStudentUsn(studentUsn);
    }
}
