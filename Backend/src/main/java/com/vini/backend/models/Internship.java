// src/main/java/com/vini/backend/models/Internship.java
package com.vini.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long internshipId;
    private String studentUsn;
    private String academicYear;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate internshipStart;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate internshipEnd;

    private String internshipDuration;
    private String internshipCertificate;
    private String internshipLocation;
    private String internshipDomain;
    private String internshipEvaluationSheet;
    private String internshipCompletionCertificateUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "internshipGuide", referencedColumnName = "facultyUid")
    private Faculty internshipGuide;
}
