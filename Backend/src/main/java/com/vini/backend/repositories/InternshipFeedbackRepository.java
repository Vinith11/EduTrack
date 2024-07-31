
// src/main/java/com/vini/backend/repositories/InternshipFeedbackRepository.java
package com.vini.backend.repositories;

import com.vini.backend.models.InternshipFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipFeedbackRepository extends JpaRepository<InternshipFeedback, Long> {
}
