package group14.backend.lms.controller;

import group14.backend.lms.model.dto.AttendanceDto;
import group14.backend.lms.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {
    @Autowired
    private IAttendanceService attendanceService;

    @GetMapping("/room/{roomId}")
    public ResponseEntity<?> getAttendanceByRoomId(@PathVariable int roomId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByRoomId(roomId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAttendance(@RequestBody AttendanceDto attendanceDto) {
        return ResponseEntity.ok(attendanceService.createAttendance(attendanceDto));
    }
}
