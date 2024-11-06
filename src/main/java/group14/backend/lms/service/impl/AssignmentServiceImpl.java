package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Assignment;
import group14.backend.lms.model.entity.Course;
import group14.backend.lms.model.entity.Question;
import group14.backend.lms.model.entity.Submission;
import group14.backend.lms.model.dto.AssignmentDto;
import group14.backend.lms.repository.IAssignmentRepository;
import group14.backend.lms.repository.ICourseRepository;
import group14.backend.lms.repository.IQuestionRepository;
import group14.backend.lms.repository.ISubmissionRepository;
import group14.backend.lms.service.IAssignmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AssignmentServiceImpl implements IAssignmentService {
    private final IAssignmentRepository assignmentRepository;
    private final ICourseRepository courseRepository;
    private final IQuestionRepository questionRepository;
    private final ISubmissionRepository submissionRepository;

    @Override
    public List<AssignmentDto> getAssignmentByCourseId(long courseId) {
        List<Assignment> assignmentList = assignmentRepository.findAssignmentsByCourseId(courseId);

        return assignmentList.stream()
                .map(AssignmentDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentDto getAssignmentById(long assignmentId) {
        return AssignmentDto.convertToDto(assignmentRepository.findById(assignmentId).orElseThrow());
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Assignment assignment = new Assignment();
        assignment.setName(assignmentDto.name());
        assignment.setDescription(assignmentDto.description());
        assignment.setStartDate(assignmentDto.startDate());
        assignment.setEndDate(assignmentDto.endDate());
        assignment.setDuration(assignmentDto.duration());
        assignment.setFileName(assignmentDto.fileName());
        assignment.setFileType(assignmentDto.fileType());
        assignment.setFile(assignmentDto.file());

        Course course = courseRepository.findById(assignmentDto.courseId()).orElseThrow();
        assignment.setCourse(course);
        course.getAssignments().add(assignment); // Add assignment to course

        // Handle Questions (using a Set to avoid duplicates)
//        Set<Question> questions = new HashSet<>((Collection<Question>) questionRepository.findAllById(assignmentDto.questionIds()));
//        questions.forEach(q -> q.setAssignment(assignment)); // Set bidirectional relationship
//        assignment.setQuestions(new ArrayList<>(questions)); // Convert back to List if needed
//
//        // Handle Submissions (using a Set to avoid duplicates)
//        Set<Submission> submissions = new HashSet<>((Collection<Submission>) submissionRepository.findAllById(assignmentDto.submissionIds()));
//        submissions.forEach(s -> s.setAssignment(assignment)); // Set bidirectional relationship
//        assignment.setSubmissions(new ArrayList<>(submissions)); // Convert back to List if needed

        return AssignmentDto.convertToDto(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentDto updateAssignment(long id, AssignmentDto assignmentDto) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow();

        assignment.setName(assignmentDto.name());
        assignment.setDescription(assignmentDto.description());
        assignment.setStartDate(assignmentDto.startDate());
        assignment.setEndDate(assignmentDto.endDate());
        assignment.setDuration(assignmentDto.duration());
        assignment.setFileName(assignmentDto.fileName());
        assignment.setFileType(assignmentDto.fileType());
        assignment.setFile(assignmentDto.file());

        // TODO update assignment

        return AssignmentDto.convertToDto(assignmentRepository.save(assignment));
    }

    @Override
    public void deleteAssignment(long id) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        assert assignment != null;
        assignmentRepository.delete(assignment);
    }
}
