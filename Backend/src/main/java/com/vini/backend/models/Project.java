package com.vini.backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @ElementCollection
    private List<String> teamMembers; // List of USNs of team members

    private String studentProjectGuideId;  // Faculty assigned as a guide
    private String studentProjectDomain;
    private String studentProjectDescription;
    private String studentProjectType;
    private String studentProjectReport;
    private LocalDate studentProjectStart;

    private String studentProjectCompletionStatus;
    private String studentProjectUrl;

    private Boolean facultyApprovalStatus;  // Approval status
}


