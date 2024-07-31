// src/main/java/com/vini/backend/repositories/FacultyRepository.java
package com.vini.backend.repositories;

import com.vini.backend.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String> {
}