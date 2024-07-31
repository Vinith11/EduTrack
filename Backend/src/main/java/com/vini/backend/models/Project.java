// src/main/java/com/vini/backend/models/Project.java
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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String studentProjectName;
    private String academicYear;
    private String studentProjectLeaderId;
    private String studentProjectGuideId;
    private String studentProjectDomain;
    private String studentProjectDescription;
    private String studentProjectType;
    private String studentProjectReport;
    private LocalDate studentProjectStart;
    private String studentProjectCompletionStatus;
    private String studentProjectUrl;
}

