package com.vini.backend.service.implementaion;

import com.vini.backend.models.Faculty;
import com.vini.backend.models.Internship;
import com.vini.backend.repositories.FacultyRepository;
import com.vini.backend.repositories.InternshipRepository;
import com.vini.backend.service.InternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternshipServiceImpl implements InternshipService {

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Override
    public Internship saveInternship(Internship internship) {
        return internshipRepository.save(internship);
    }


    @Override
    public Internship updateInternship(Long id, Internship internship) {
        Optional<Internship> optionalInternship = internshipRepository.findById(id);
        if (optionalInternship.isPresent()) {
            Internship existingInternship = optionalInternship.get();

            // Update existing internship details
            existingInternship.setStudentUsn(internship.getStudentUsn());
            existingInternship.setAcademicYear(internship.getAcademicYear());
            existingInternship.setInternshipStart(internship.getInternshipStart());
            existingInternship.setInternshipEnd(internship.getInternshipEnd());
            existingInternship.setInternshipDuration(internship.getInternshipDuration());
            existingInternship.setInternshipCertificate(internship.getInternshipCertificate());
            existingInternship.setInternshipLocation(internship.getInternshipLocation());
            existingInternship.setInternshipDomain(internship.getInternshipDomain());
            existingInternship.setInternshipEvaluationSheet(internship.getInternshipEvaluationSheet());
            existingInternship.setInternshipCompletionCertificateUrl(internship.getInternshipCompletionCertificateUrl());

            // Update Faculty if present
            if (internship.getInternshipGuide() != null) {
                Faculty newGuide = facultyRepository.findById(internship.getInternshipGuide().getFacultyUid())
                        .orElseThrow(() -> new RuntimeException("Faculty not found"));
                existingInternship.setInternshipGuide(newGuide);
            }

            return internshipRepository.save(existingInternship);
        } else {
            throw new RuntimeException("Internship not found with id " + id);
        }
    }


    @Override
    public void deleteInternship(Long id) {
        internshipRepository.deleteById(id);
    }

    @Override
    public Internship getInternshipById(Long id) {
        return internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Internship not found with id " + id));
    }

    @Override
    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }
}
