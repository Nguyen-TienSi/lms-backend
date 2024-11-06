package group14.backend.lms.service;

import group14.backend.lms.model.dto.RoomDto;

import java.util.List;

public interface IRoomService {
    List<RoomDto> getRoomsByCourseId(long courseId);
    RoomDto getRoomById(long roomId);
    RoomDto createRoom(RoomDto roomDto);
    RoomDto updateRoom(long id, RoomDto roomDto);
    void deleteRoomById(long roomId);
}
