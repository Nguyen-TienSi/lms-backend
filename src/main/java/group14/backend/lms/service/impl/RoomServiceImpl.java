package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Attendance;
import group14.backend.lms.model.entity.Course;
import group14.backend.lms.model.entity.Room;
import group14.backend.lms.model.dto.RoomDto;
import group14.backend.lms.repository.IAttendanceRepository;
import group14.backend.lms.repository.ICourseRepository;
import group14.backend.lms.repository.IRoomRepository;
import group14.backend.lms.service.IRoomService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements IRoomService {
    private final IRoomRepository roomRepository;
    private final ICourseRepository courseRepository;
    private final IAttendanceRepository attendanceRepository;

    @Override
    public List<RoomDto> getRoomsByCourseId(long courseId) {
        return roomRepository.findRoomsByCourseId(courseId).stream()
                .map(RoomDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(long roomId) {
        return roomRepository.findById(roomId)
                .map(RoomDto::convertToDto)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + roomId));
    }

    @Override
    public RoomDto createRoom(RoomDto roomDto) {
        Room room = new Room();
        room.setStartTime(roomDto.startTime());
        room.setAttendance(roomDto.attendance());

        Course course = courseRepository.findById(roomDto.courseId()).orElseThrow();
        course.getRooms().add(room);
        room.setCourse(course);

        return RoomDto.convertToDto(room);
    }

    @Override
    public RoomDto updateRoom(long id, RoomDto roomDto) {
        Room room = roomRepository.findById(id).orElseThrow();
        // TODO update room
        return RoomDto.convertToDto(room);
    }

    @Override
    public void deleteRoomById(long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + roomId));
        roomRepository.delete(room);
    }
}
