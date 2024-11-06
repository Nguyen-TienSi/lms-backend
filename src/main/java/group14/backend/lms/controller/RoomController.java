package group14.backend.lms.controller;

import group14.backend.lms.model.dto.RoomDto;
import group14.backend.lms.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private IRoomService roomService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getRoomByCourseId(@PathVariable int courseId) {
        return ResponseEntity.ok(roomService.getRoomsByCourseId(courseId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoomByRoomId(@PathVariable int roomId) {
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRoom(@RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.createRoom(roomDto));
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto, @PathVariable int roomId) {
        return ResponseEntity.ok(roomService.updateRoom(roomId, roomDto));
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable int roomId) {
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }
}
