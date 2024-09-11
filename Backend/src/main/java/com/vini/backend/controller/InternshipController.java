package com.vini.backend.controller;

import com.vini.backend.exception.UserException;
import com.vini.backend.models.Internship;
import com.vini.backend.models.Student;
import com.vini.backend.response.ApiResponse;
import com.vini.backend.service.InternshipService;
import com.vini.backend.service.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internship")
public class InternshipController {

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private StudentUserService studentUserService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createInternship(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Internship internship) throws Exception {
        // Extract student details from JWT
        System.out.println(internship);
        Student student = studentUserService.findUserProfileByJwt(jwt);
        internship.setStudentUsn(student.getUsn());

        Internship newInternship = internshipService.saveInternship(internship);

        ApiResponse res= new ApiResponse("Internship Added Successfully",true);

        return ResponseEntity.ok(res);
    }


    // Update internship with JWT token
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateInternship(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id,
            @RequestBody Internship internship) throws UserException{
        // Ensure student from JWT is performing the update
        Student student = studentUserService.findUserProfileByJwt(jwt);
        internship.setStudentUsn(student.getUsn());

        Internship updatedInternship = internshipService.updateInternship(id, internship);

        ApiResponse res= new ApiResponse("Internship Updated Successfully",true);

        return ResponseEntity.ok(res);
    }

    // Delete internship
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteInternship(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) {
        // You could add JWT-based authorization checks here
        internshipService.deleteInternship(id);

        ApiResponse res= new ApiResponse("Internship Deleted Successfully",true);

        return ResponseEntity.ok(res);
    }

    // Get internship by ID
    @GetMapping("/{id}")
    public ResponseEntity<Internship> getInternshipById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id) {
        // You could add JWT-based authorization checks here
        Internship internship = internshipService.getInternshipById(id);
        return ResponseEntity.ok(internship);
    }

    // Get all internships
    @GetMapping("/all")
    public ResponseEntity<List<Internship>> getAllInternships(
            @RequestHeader("Authorization") String jwt) {
        // You could add JWT-based authorization checks here
        List<Internship> internships = internshipService.getAllInternships();
        return ResponseEntity.ok(internships);
    }

}
