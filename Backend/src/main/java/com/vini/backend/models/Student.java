// src/main/java/com/vini/backend/models/Student.java
package com.vini.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String usn;
    private String studentName;
    private String studentPhone;

    @Id
    private String studentEmail;
    private String studentBatch;
    private String studentPassword;

    @ManyToOne
    private Project project;
}
