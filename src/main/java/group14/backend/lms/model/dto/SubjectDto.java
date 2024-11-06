package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Subject;
import group14.backend.lms.model.entity.Teacher;
import lombok.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record SubjectDto(
        Long id,
        String name,
        Set<Long> teacherIds
) {
    public static SubjectDto convertToDto(Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .teacherIds(subject.getTeachers().stream()
                        .map(Teacher::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
