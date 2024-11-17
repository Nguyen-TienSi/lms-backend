package group14.backend.lms.service;

import group14.backend.lms.model.dto.SubmissionDto;

import java.util.List;

public interface ISubmissionService {
    List<SubmissionDto> getSubmissionsByAssignmentId(long assignmentId);
    List<SubmissionDto> getSubmissionByStudentId(long studentId);
    SubmissionDto getSubmissionByAssignmentIdAndStudentId(long assignmentId, long studentId);
    SubmissionDto createSubmission(SubmissionDto submissionDto);
}
