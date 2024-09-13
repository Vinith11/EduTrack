package com.vini.backend.controller;


import com.vini.backend.models.InternshipEvaluation;
import com.vini.backend.response.ApiResponse;
import com.vini.backend.service.InternshipEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internship-evaluation")
public class InternshipEvaluationController {

    @Autowired
    private InternshipEvaluationService internshipEvaluationService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createInternshipEvaluation(
            @RequestBody InternshipEvaluation evaluation) {
        internshipEvaluationService.createInternshipEvaluation(evaluation);
        ApiResponse res = new ApiResponse("Evaluation Created Successfully", true);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateInternshipEvaluation(
            @PathVariable Long id,
            @RequestBody InternshipEvaluation evaluation) {
        internshipEvaluationService.updateInternshipEvaluation(id, evaluation);
        ApiResponse res = new ApiResponse("Evaluation Updated Successfully", true);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteInternshipEvaluation(
            @PathVariable Long id) {
        internshipEvaluationService.deleteInternshipEvaluation(id);
        ApiResponse res = new ApiResponse("Evaluation Deleted Successfully", true);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipEvaluation> getInternshipEvaluationById(
            @PathVariable Long id) {
        InternshipEvaluation evaluation = internshipEvaluationService.getInternshipEvaluationById(id);
        return ResponseEntity.ok(evaluation);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InternshipEvaluation>> getAllInternshipEvaluations() {
        List<InternshipEvaluation> evaluations = internshipEvaluationService.getAllInternshipEvaluations();
        return ResponseEntity.ok(evaluations);
    }
}
