package com.vini.backend.service.implementaion;


import com.vini.backend.models.InternshipEvaluation;
import com.vini.backend.repositories.InternshipEvaluationRepository;
import com.vini.backend.service.InternshipEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternshipEvaluationServiceImpl implements InternshipEvaluationService {

    @Autowired
    private InternshipEvaluationRepository internshipEvaluationRepository;

    @Override
    public InternshipEvaluation createInternshipEvaluation(InternshipEvaluation evaluation) {
        return internshipEvaluationRepository.save(evaluation);
    }

    @Override
    public InternshipEvaluation updateInternshipEvaluation(Long id, InternshipEvaluation evaluation) {
        Optional<InternshipEvaluation> optionalEvaluation = internshipEvaluationRepository.findById(id);
        if (optionalEvaluation.isPresent()) {
            InternshipEvaluation existingEvaluation = optionalEvaluation.get();
            existingEvaluation.setEvaluationDate(evaluation.getEvaluationDate());
            existingEvaluation.setEvaluationScore(evaluation.getEvaluationScore());
            existingEvaluation.setFacultyEvaluationStatus(evaluation.getFacultyEvaluationStatus());
            existingEvaluation.setFacultyEvaluationYear(evaluation.getFacultyEvaluationYear());
            existingEvaluation.setInternship(evaluation.getInternship());
            existingEvaluation.setEvaluator(evaluation.getEvaluator());

            return internshipEvaluationRepository.save(existingEvaluation);
        } else {
            throw new RuntimeException("Evaluation not found with id " + id);
        }
    }

    @Override
    public void deleteInternshipEvaluation(Long id) {
        internshipEvaluationRepository.deleteById(id);
    }

    @Override
    public InternshipEvaluation getInternshipEvaluationById(Long id) {
        return internshipEvaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found with id " + id));
    }

    @Override
    public List<InternshipEvaluation> getAllInternshipEvaluations() {
        return internshipEvaluationRepository.findAll();
    }
}
