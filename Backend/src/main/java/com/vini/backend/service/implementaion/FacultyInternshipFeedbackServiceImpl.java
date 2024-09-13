package com.vini.backend.service.implementaion;


import com.vini.backend.models.FacultyInternshipFeedback;
import com.vini.backend.repositories.FacultyInternshipFeedbackRepository;
import com.vini.backend.service.FacultyInternshipFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyInternshipFeedbackServiceImpl implements FacultyInternshipFeedbackService {

    @Autowired
    private FacultyInternshipFeedbackRepository feedbackRepository;

    @Override
    public FacultyInternshipFeedback createFeedback(FacultyInternshipFeedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public FacultyInternshipFeedback updateFeedback(Long id, FacultyInternshipFeedback feedback) {
        Optional<FacultyInternshipFeedback> optionalFeedback = feedbackRepository.findById(id);
        if (optionalFeedback.isPresent()) {
            FacultyInternshipFeedback existingFeedback = optionalFeedback.get();
            existingFeedback.setFeedbackText(feedback.getFeedbackText());
            existingFeedback.setFeedbackDate(feedback.getFeedbackDate());
            existingFeedback.setInternship(feedback.getInternship());
            existingFeedback.setFaculty(feedback.getFaculty());

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
    public FacultyInternshipFeedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found with id " + id));
    }

    @Override
    public List<FacultyInternshipFeedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
}
