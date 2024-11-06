package group14.backend.lms.controller;

import group14.backend.lms.model.dto.TeacherDto;
import group14.backend.lms.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;

    @GetMapping("")
    public ResponseEntity<?> getTeachers(){
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<?> getTeacher(@PathVariable int teacherId){
        return ResponseEntity.ok(teacherService.getTeacherById(teacherId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeacher(@RequestBody TeacherDto teacherDto){
        return ResponseEntity.ok(teacherService.createTeacher(teacherDto));
    }

    @PutMapping("update/{teacherId}")
    public ResponseEntity<?> updateTeacher(@PathVariable long teacherId, @RequestBody TeacherDto teacherDto){
        return ResponseEntity.ok(teacherService.updateTeacher(teacherId, teacherDto));
    }
}
