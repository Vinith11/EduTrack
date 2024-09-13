package com.vini.backend.controller;


import com.vini.backend.models.FacultyInternshipFeedback;
import com.vini.backend.response.ApiResponse;
import com.vini.backend.service.FacultyInternshipFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-feedback")
public class FacultyInternshipFeedbackController {

    @Autowired
    private FacultyInternshipFeedbackService feedbackService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createFeedback(
            @RequestBody FacultyInternshipFeedback feedback) {
        feedbackService.createFeedback(feedback);
        ApiResponse res = new ApiResponse("Feedback Created Successfully", true);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateFeedback(
            @PathVariable Long id,
            @RequestBody FacultyInternshipFeedback feedback) {
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
    public ResponseEntity<FacultyInternshipFeedback> getFeedbackById(
            @PathVariable Long id) {
        FacultyInternshipFeedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FacultyInternshipFeedback>> getAllFeedbacks() {
        List<FacultyInternshipFeedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }
}
