package com.vini.backend.service.implementaion;

import com.vini.backend.models.Internship;
import com.vini.backend.models.Student;
import com.vini.backend.models.StudentInternshipFeedback;
import com.vini.backend.repositories.StudentInternshipFeedbackRepository;
import com.vini.backend.repositories.StudentRepository;
import com.vini.backend.repositories.InternshipRepository;
import com.vini.backend.service.StudentInternshipFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentInternshipFeedbackServiceImpl implements StudentInternshipFeedbackService {

    @Autowired
    private StudentInternshipFeedbackRepository feedbackRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @Override
    public StudentInternshipFeedback createFeedback(StudentInternshipFeedback feedback) {
        // Fetch the associated Student by 'usn'
        Student student = studentRepository.findById(feedback.getStudent().getUsn())
                .orElseThrow(() -> new RuntimeException("Student not found with USN " + feedback.getStudent().getUsn()));

        // Fetch the associated Internship by 'internshipId'
        Internship internship = internshipRepository.findById(feedback.getInternship().getInternshipId())
                .orElseThrow(() -> new RuntimeException("Internship not found with ID " + feedback.getInternship().getInternshipId()));

        // Set the fetched entities in the feedback
        feedback.setStudent(student);
        feedback.setInternship(internship);

        return feedbackRepository.save(feedback);
    }

    @Override
    public StudentInternshipFeedback updateFeedback(Long id, StudentInternshipFeedback feedback) {
        Optional<StudentInternshipFeedback> optionalFeedback = feedbackRepository.findById(id);
        if (optionalFeedback.isPresent()) {
            StudentInternshipFeedback existingFeedback = optionalFeedback.get();

            // Fetch the associated Student and Internship
            Student student = studentRepository.findById(feedback.getStudent().getUsn())
                    .orElseThrow(() -> new RuntimeException("Student not found with USN " + feedback.getStudent().getUsn()));
            Internship internship = internshipRepository.findById(feedback.getInternship().getInternshipId())
                    .orElseThrow(() -> new RuntimeException("Internship not found with ID " + feedback.getInternship().getInternshipId()));

            // Set the fetched entities and updated fields
            existingFeedback.setFeedbackText(feedback.getFeedbackText());
            existingFeedback.setFeedbackDate(feedback.getFeedbackDate());
            existingFeedback.setStudent(student);
            existingFeedback.setInternship(internship);

            return feedbackRepository.save(existingFeedback);
        } else {
            throw new RuntimeException("Feedback not found with id " + id);
        }
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public StudentInternshipFeedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id " + id));
    }

    @Override
    public List<StudentInternshipFeedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
}
