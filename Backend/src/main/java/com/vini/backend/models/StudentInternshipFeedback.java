// src/main/java/com/vini/backend/models/StudentInternshipFeedback.java
package com.vini.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInternshipFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;
    private String feedbackText;
    private LocalDate feedbackDate;

    @ManyToOne
    @JoinColumn(name = "internshipId", referencedColumnName = "internshipId")
    private Internship internship;

    @ManyToOne
    @JoinColumn(name = "studentUsn", referencedColumnName = "usn")
    private Student student;
}

