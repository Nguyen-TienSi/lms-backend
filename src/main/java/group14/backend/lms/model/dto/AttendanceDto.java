package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Attendance;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AttendanceDto(
        Long id,
        Long studentId,
        Long roomId,
        Attendance.Status status,
        LocalDateTime attendanceTime
) {
    public static AttendanceDto convertToDto(Attendance attendance) {
        return AttendanceDto.builder()
                .id(attendance.getId())
                .studentId(attendance.getStudent().getId())
                .roomId(attendance.getRoom().getId())
                .status(attendance.getStatus())
                .attendanceTime(attendance.getAttendanceTime())
                .build();
    }
}
