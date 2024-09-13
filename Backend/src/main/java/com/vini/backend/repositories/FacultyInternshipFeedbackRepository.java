
// src/main/java/com/vini/backend/repositories/InternshipFeedbackRepository.java
package com.vini.backend.repositories;

import com.vini.backend.models.FacultyInternshipFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyInternshipFeedbackRepository extends JpaRepository<FacultyInternshipFeedback, Long> {
}
