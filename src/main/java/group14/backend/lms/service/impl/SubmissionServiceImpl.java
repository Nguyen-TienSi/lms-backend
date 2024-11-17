package group14.backend.lms.service.impl;

import group14.backend.lms.model.dto.SubmissionDto;
import group14.backend.lms.model.entity.*;
import group14.backend.lms.repository.IAnswerRepository;
import group14.backend.lms.repository.IAssignmentRepository;
import group14.backend.lms.repository.IStudentRepository;
import group14.backend.lms.repository.ISubmissionRepository;
import group14.backend.lms.service.ISubmissionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubmissionServiceImpl implements ISubmissionService {
    private final ISubmissionRepository submissionRepository;
    private final IAssignmentRepository assignmentRepository;
    private final IAnswerRepository answerRepository;

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
    public SubmissionDto getSubmissionByAssignmentIdAndStudentId(long assignmentId, long studentId) {
        return SubmissionDto.convertToDto(submissionRepository.findSubmissionsByAssignmentIdAndStudentId(assignmentId, studentId));
    }

    @Override
    public SubmissionDto createSubmission(SubmissionDto submissionDto) {
        // TODO create submission
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Submission submission = new Submission();
        if (authentication != null && authentication.getPrincipal() instanceof Student student) {
            submission.setStudent(student);
        }
        Assignment assignment = assignmentRepository.findById(submissionDto.assignmentId()).orElseThrow();
        submission.setAssignment(assignment);
        assignment.getSubmissions().add(submission);

        submissionDto.answerIds().forEach(answerId -> {
            Answer answer = answerRepository.findById(answerId).orElseThrow();
            submission.getAnswers().add(answer);
        });

        submission.setSubmissionTime(LocalDateTime.now());

        return SubmissionDto.convertToDto(submissionRepository.save(submission));
    }
}
