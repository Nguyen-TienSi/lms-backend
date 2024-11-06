package group14.backend.lms.service;

import group14.backend.lms.model.dto.AssignmentDto;

import java.util.List;

public interface IAssignmentService {
    List<AssignmentDto> getAssignmentByCourseId(long courseId);
    AssignmentDto getAssignmentById(long assignmentId);
    AssignmentDto createAssignment(AssignmentDto assignmentDto);
    AssignmentDto updateAssignment(long id, AssignmentDto assignmentDto);
    void deleteAssignment(long id);
}
