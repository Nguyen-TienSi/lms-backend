package group14.backend.lms.service;

import group14.backend.lms.model.dto.AttendanceDto;

import java.util.List;

public interface IAttendanceService {
    List<AttendanceDto> getAttendanceByRoomId(long roomId);
    AttendanceDto createAttendance(AttendanceDto attendanceDto);
}
