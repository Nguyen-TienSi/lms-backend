package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Attendance;
import group14.backend.lms.model.entity.Course;
import group14.backend.lms.model.entity.Student;
import group14.backend.lms.model.entity.Submission;
import lombok.Builder;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record StudentDto(
        UserDto userDto,
        Long classId,
        Set<Long> courseIds,
        Set<Long> attendanceIds,
        Set<Long> submissionIds
) {
    public static StudentDto convertToDto(Student student) {
        Long classId = student.getAClass() != null ? student.getAClass().getId() : null;
        return StudentDto.builder()
                .userDto(UserDto.convertToDto(student))
//                .classId(student.getAClass().getId())
                .classId(classId)
                .courseIds(student.getCourses().stream()
                        .map(Course::getId)
                        .collect(Collectors.toSet()))
                .attendanceIds(student.getAttendances().stream()
                        .map(Attendance::getId)
                        .collect(Collectors.toSet()))
                .submissionIds(student.getSubmissions().stream()
                        .map(Submission::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
