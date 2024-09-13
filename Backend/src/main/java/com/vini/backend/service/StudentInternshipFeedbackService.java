package com.vini.backend.service;

import com.vini.backend.models.StudentInternshipFeedback;

import java.util.List;

public interface StudentInternshipFeedbackService {
    StudentInternshipFeedback createFeedback(StudentInternshipFeedback feedback);
    StudentInternshipFeedback updateFeedback(Long id, StudentInternshipFeedback feedback);
    void deleteFeedback(Long id);
    StudentInternshipFeedback getFeedbackById(Long id);
    List<StudentInternshipFeedback> getAllFeedbacks();
}