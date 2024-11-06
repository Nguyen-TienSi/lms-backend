package group14.backend.lms.controller;

import group14.backend.lms.model.dto.CourseDto;
import group14.backend.lms.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping("")
    public ResponseEntity<?> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable int courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<?> getCourseByTeacherId(@PathVariable int teacherId) {
        return ResponseEntity.ok(courseService.getCourseByTeacherId(teacherId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getCourseByStudentId(@PathVariable int studentId) {
        return ResponseEntity.ok(courseService.getCourseByStudentId(studentId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.createCourse(courseDto));
    }

    @PutMapping("update/{courseId}")
    public ResponseEntity<?> updateCourse(@RequestBody CourseDto courseDto, @PathVariable int courseId) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, courseDto));
    }

    @DeleteMapping("delete/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
}
