package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Assignment;
import group14.backend.lms.model.entity.Course;
import group14.backend.lms.model.entity.Room;
import group14.backend.lms.model.entity.Student;
import lombok.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record CourseDto(
        Long id,
        Long classId,
        Long subjectId,
        Long teacherId,
        Long semesterId,
        Set<Long> studentIds,
        Set<Long> roomIds,
        Set<Long> assignmentIds
) {
    public static CourseDto convertToDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .classId(course.getAClass().getId())
                .subjectId(course.getSubject().getId())
                .teacherId(course.getTeacher().getId())
                .semesterId(course.getSemester().getId())
                .studentIds(course.getStudents().stream()
                        .map(Student::getId)
                        .collect(Collectors.toSet()))
                .roomIds(course.getRooms().stream()
                        .map(Room::getId)
                        .collect(Collectors.toSet()))
                .assignmentIds(course.getAssignments().stream()
                        .map(Assignment::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
