
// src/main/java/com/vini/backend/repositories/StudentInternshipFeedbackRepository.java
package com.vini.backend.repositories;

import com.vini.backend.models.StudentInternshipFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInternshipFeedbackRepository extends JpaRepository<StudentInternshipFeedback, Long> {
}