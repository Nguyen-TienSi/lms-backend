package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Course;
import group14.backend.lms.model.entity.Subject;
import group14.backend.lms.model.entity.Teacher;
import lombok.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record TeacherDto(
        UserDto userDto,
        Set<Long> subjectIds,
        Set<Long> courseIds
) {
    public static TeacherDto convertToDto(Teacher teacher) {
        return TeacherDto.builder()
                .userDto(UserDto.convertToDto(teacher))
                .subjectIds(teacher.getSubjects().stream()
                        .map(Subject::getId)
                        .collect(Collectors.toSet()))
                .courseIds(teacher.getCourses().stream()
                        .map(Course::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
