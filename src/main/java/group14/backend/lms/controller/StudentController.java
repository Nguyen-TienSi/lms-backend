package group14.backend.lms.controller;

import group14.backend.lms.model.dto.StudentDto;
import group14.backend.lms.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public ResponseEntity<?> getStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> getStudent(@PathVariable int studentId) {
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.createStudent(studentDto));
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable int studentId, @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, studentDto));
    }
}
