
// src/main/java/com/vini/backend/repositories/InternshipRepository.java
package com.vini.backend.repositories;

import com.vini.backend.models.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipRepository extends JpaRepository<Internship, Long> {
}
