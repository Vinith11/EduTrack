package com.vini.backend.controller;

import com.vini.backend.models.StudentInternshipFeedback;
import com.vini.backend.response.ApiResponse;
import com.vini.backend.service.StudentInternshipFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-feedback")
public class StudentInternshipFeedbackController {

    @Autowired
    private StudentInternshipFeedbackService feedbackService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createFeedback(
            @RequestBody StudentInternshipFeedback feedback) {
        feedbackService.createFeedback(feedback);
        ApiResponse res = new ApiResponse("Feedback Created Successfully", true);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateFeedback(
            @PathVariable Long id,
            @RequestBody StudentInternshipFeedback feedback) {
        feedbackService.updateFeedback(id, feedback);
        ApiResponse res = new ApiResponse("Feedback Updated Successfully", true);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteFeedback(
            @PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        ApiResponse res = new ApiResponse("Feedback Deleted Successfully", true);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentInternshipFeedback> getFeedbackById(
            @PathVariable Long id) {
        StudentInternshipFeedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentInternshipFeedback>> getAllFeedbacks() {
        List<StudentInternshipFeedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }
}
