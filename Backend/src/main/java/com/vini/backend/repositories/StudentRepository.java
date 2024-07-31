// src/main/java/com/vini/backend/repositories/StudentRepository.java
package com.vini.backend.repositories;

import com.vini.backend.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
}
