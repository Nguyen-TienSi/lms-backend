package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.*;
import group14.backend.lms.model.dto.AssignmentDto;
import group14.backend.lms.repository.IAssignmentRepository;
import group14.backend.lms.repository.ICourseRepository;
import group14.backend.lms.repository.IQuestionRepository;
import group14.backend.lms.repository.ISubmissionRepository;
import group14.backend.lms.service.IAssignmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
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
    private final String uploadDir = "/uploads";

    @Override
    public List<AssignmentDto> getAssignmentByCourseId(long courseId) {
        return assignmentRepository.findAssignmentsByCourseId(courseId).stream()
                .map(AssignmentDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AssignmentDto getAssignmentById(long assignmentId) {
        return AssignmentDto.convertToDto(assignmentRepository.findById(assignmentId).orElseThrow());
    }

    @Override
    public AssignmentDto createAssignment(AssignmentDto assignmentDto) throws IOException {
        Assignment assignment = new Assignment();
        assignment.setName(assignmentDto.name());
        assignment.setDescription(assignmentDto.description());
        assignment.setStartDate(assignmentDto.startDate());
        assignment.setEndDate(assignmentDto.endDate());
        if (assignmentDto.duration() != null) {
            assignment.setDuration(assignmentDto.duration());
        } else {
            assignment.setDuration(Duration.between(assignmentDto.startDate(), assignmentDto.endDate()));
        }

//        if (assignmentDto.file() != null && assignmentDto.file().length > 0) {
//            String fileName = StringUtils.cleanPath(UUID.randomUUID() + ".pdf");
//            Path filePath = Paths.get(uploadDir, fileName);
//            Files.copy(new ByteArrayInputStream(assignmentDto.file()), filePath, StandardCopyOption.REPLACE_EXISTING);
//            assignment.setFilePath(fileName);
//        }

        Course course = courseRepository.findById(assignmentDto.courseId()).orElseThrow();
        assignment.setCourse(course);
        course.getAssignments().add(assignment); // Add assignment to course

        if (assignmentDto.questions() != null) {
            List<Question> questions = assignmentDto.questions().stream()
                    .map(questionDto -> {
                        Question question = new Question();
                        question.setQuestion(questionDto.question());
                        question.setAssignment(assignment);

                        if (questionDto.answerDtoList() != null) {
                            question.setAnswer(questionDto.answerDtoList().stream().map(answerDto -> {
                                Answer answer = new Answer();
                                answer.setAnswer(answerDto.answer());
                                answer.setQuestion(question);
                                answer.setIsCorrect(answerDto.isCorrect());
                                return answer;
                            }).collect(Collectors.toList()));
                        }
                        return question;
                    }).collect(Collectors.toList());
            assignment.setQuestions(questions);
        }

        return AssignmentDto.convertToDto(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentDto updateAssignment(long id, AssignmentDto assignmentDto) {
        Assignment assignment = assignmentRepository.findById(id).orElseThrow();

//        assignment.setName(assignmentDto.name());
//        assignment.setDescription(assignmentDto.description());
//        assignment.setStartDate(assignmentDto.startDate());
//        assignment.setEndDate(assignmentDto.endDate());
//        assignment.setDuration(assignmentDto.duration());
//        assignment.setFileName(assignmentDto.fileName());
//        assignment.setFileType(assignmentDto.fileType());
//        assignment.setFile(assignmentDto.file());

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
