// src/main/java/com/vini/backend/repositories/ProjectEvaluationRepository.java
package com.vini.backend.repositories;

import com.vini.backend.models.ProjectEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectEvaluationRepository extends JpaRepository<ProjectEvaluation, Long> {
}
