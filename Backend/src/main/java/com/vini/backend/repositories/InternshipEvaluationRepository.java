// src/main/java/com/vini/backend/repositories/InternshipEvaluationRepository.java
package com.vini.backend.repositories;

import com.vini.backend.models.internship.InternshipEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipEvaluationRepository extends JpaRepository<InternshipEvaluation, Long> {
}
