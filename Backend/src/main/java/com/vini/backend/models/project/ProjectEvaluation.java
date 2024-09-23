// src/main/java/com/vini/backend/models/ProjectEvaluation.java
package com.vini.backend.models.project;

import com.vini.backend.models.Faculty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;
    private LocalDate evaluationDate;
    private Integer evaluationScore;

    @ManyToOne
    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "evaluatorId", referencedColumnName = "facultyUid")
    private Faculty evaluator;
}

