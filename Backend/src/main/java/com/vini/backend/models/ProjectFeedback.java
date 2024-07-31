// src/main/java/com/vini/backend/models/ProjectFeedback.java
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
public class ProjectFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;
    private String feedbackText;
    private LocalDate feedbackDate;

    @ManyToOne
    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "facultyUid", referencedColumnName = "facultyUid")
    private Faculty faculty;
}
