package com.vini.backend.service;

import com.vini.backend.models.FacultyInternshipFeedback;

import java.util.List;

public interface FacultyInternshipFeedbackService {
    FacultyInternshipFeedback createFeedback(FacultyInternshipFeedback feedback);
    FacultyInternshipFeedback updateFeedback(Long id, FacultyInternshipFeedback feedback);
    void deleteFeedback(Long id);
    FacultyInternshipFeedback getFeedbackById(Long id);
    List<FacultyInternshipFeedback> getAllFeedbacks();
}