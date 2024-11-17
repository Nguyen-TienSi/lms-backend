package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Attendance;
import group14.backend.lms.model.entity.Room;
import group14.backend.lms.model.entity.Student;
import group14.backend.lms.model.dto.AttendanceDto;
import group14.backend.lms.model.entity.User;
import group14.backend.lms.repository.IAttendanceRepository;
import group14.backend.lms.repository.IRoomRepository;
import group14.backend.lms.repository.IStudentRepository;
import group14.backend.lms.service.IAttendanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendanceServiceImpl implements IAttendanceService {
    private final IAttendanceRepository attendanceRepository;
    private final IStudentRepository studentRepository;
    private final IRoomRepository roomRepository;

    @Override
    public List<AttendanceDto> getAttendanceByRoomId(long roomId) {
        return attendanceRepository.findAttendanceByRoomId(roomId)
                .stream()
                .map(AttendanceDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AttendanceDto createAttendance(AttendanceDto attendanceDto) {
        Attendance attendance = new Attendance();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User user) {
            Student student = studentRepository.findById(user.getId()).orElseThrow();
            attendance.setStudent(student);
            student.getAttendances().add(attendance);
        }

        Room room = roomRepository.findById(attendanceDto.roomId()).orElseThrow();
        attendance.setRoom(room);
        room.getAttendances().add(attendance);

        attendance.setAttendanceTime(LocalDateTime.now());
        return AttendanceDto.convertToDto(attendanceRepository.save(attendance));
    }
}
