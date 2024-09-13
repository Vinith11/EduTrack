package com.vini.backend.service;

import com.vini.backend.models.Project;
import com.vini.backend.models.Student;

import java.util.List;

public interface EmailService {
    void sendApprovalRequestEmail(String toEmail, Project project) throws Exception;
    void sendRejectionEmail(String toEmail, Project project) throws Exception;
    void sendAcceptEmail(String toEmail, Project project) throws Exception;

}

