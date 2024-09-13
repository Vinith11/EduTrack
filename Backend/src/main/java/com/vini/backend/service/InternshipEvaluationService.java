package com.vini.backend.service;

import com.vini.backend.models.InternshipEvaluation;

import java.util.List;

public interface InternshipEvaluationService {
    InternshipEvaluation createInternshipEvaluation(InternshipEvaluation evaluation);
    InternshipEvaluation updateInternshipEvaluation(Long id, InternshipEvaluation evaluation);
    void deleteInternshipEvaluation(Long id);
    InternshipEvaluation getInternshipEvaluationById(Long id);
    List<InternshipEvaluation> getAllInternshipEvaluations();
}