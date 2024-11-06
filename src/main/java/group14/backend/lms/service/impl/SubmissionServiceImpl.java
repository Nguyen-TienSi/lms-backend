package group14.backend.lms.service.impl;

import group14.backend.lms.model.dto.SubmissionDto;
import group14.backend.lms.model.entity.Submission;
import group14.backend.lms.repository.IAssignmentRepository;
import group14.backend.lms.repository.IStudentRepository;
import group14.backend.lms.repository.ISubmissionRepository;
import group14.backend.lms.service.ISubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements ISubmissionService {
    private final ISubmissionRepository submissionRepository;
    private final IAssignmentRepository assignmentRepository;
    private final IStudentRepository studentRepository;

    @Override
    public List<SubmissionDto> getSubmissionsByAssignmentId(long assignmentId) {
        return submissionRepository.findSubmissionsByAssignmentId(assignmentId).stream()
                .map(SubmissionDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubmissionDto> getSubmissionByStudentId(long studentId) {
        return submissionRepository.findSubmissionsByStudentId(studentId).stream()
                .map(SubmissionDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubmissionDto createSubmission(SubmissionDto submission) {
        // TODO create submission

        return null;
    }
}
