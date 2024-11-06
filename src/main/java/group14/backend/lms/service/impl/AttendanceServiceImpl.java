package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Attendance;
import group14.backend.lms.model.entity.Room;
import group14.backend.lms.model.entity.Student;
import group14.backend.lms.model.dto.AttendanceDto;
import group14.backend.lms.repository.IAttendanceRepository;
import group14.backend.lms.repository.IRoomRepository;
import group14.backend.lms.repository.IStudentRepository;
import group14.backend.lms.service.IAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements IAttendanceService {
    private final IAttendanceRepository attendanceRepository;
    private final IStudentRepository studentRepository;
    private final IRoomRepository roomRepository;

    @Override
    public List<AttendanceDto> getAttendanceByRoomId(long roomId) {
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByRoomId(roomId);
        return attendanceList.stream()
                .map(AttendanceDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {
        Attendance attendance = new Attendance();
        Student student = studentRepository.findById(attendanceDto.studentId()).orElseThrow();
        attendance.setStudent(student);
        Room room = roomRepository.findById(attendanceDto.roomId()).orElseThrow();
        attendance.setRoom(room);
        attendance.setStatus(attendanceDto.status());
        attendance.setAttendanceTime(attendanceDto.attendanceTime());
        return AttendanceDto.convertToDto(attendanceRepository.save(attendance));
    }
}
