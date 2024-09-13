package com.vini.backend.service;


import com.vini.backend.models.Project;
import com.vini.backend.models.Student;

import java.util.List;

public interface ProjectService {
    Project createProjectGroup(Project projectRequest, List<String> studentIds) throws Exception;
    void updateFacultyApproval(Long projectId, boolean isApproved) throws Exception;
    void completeProject(Long projectId);
}
