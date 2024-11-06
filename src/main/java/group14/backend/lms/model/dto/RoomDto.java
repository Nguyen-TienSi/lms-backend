package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Attendance;
import group14.backend.lms.model.entity.Room;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record RoomDto(
        Long id,
        LocalDateTime startTime,
        Integer attendance,
        Long courseId,
        Set<Long> attendanceIds
) {
    public static RoomDto convertToDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .startTime(room.getStartTime())
                .attendance(room.getAttendance())
                .courseId(room.getCourse().getId())
                .attendanceIds(room.getAttendances().stream()
                        .map(Attendance::getId)
                        .collect(Collectors.toSet()))
                .build();
    }
}
