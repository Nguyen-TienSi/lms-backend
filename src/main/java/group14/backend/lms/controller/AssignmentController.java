package group14.backend.lms.controller;

import group14.backend.lms.model.dto.AssignmentDto;
import group14.backend.lms.service.IAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    @Autowired
    private IAssignmentService assignmentService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getAssignmentsByCourse(@PathVariable int courseId) {
        return ResponseEntity.ok(assignmentService.getAssignmentByCourseId(courseId));
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<?> getAssignmentById(@PathVariable int assignmentId) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(assignmentId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAssignment(@RequestBody AssignmentDto assignmentDto) throws IOException {
        return ResponseEntity.ok(assignmentService.createAssignment(assignmentDto));
    }

    @PutMapping("/update/{assignmentId}")
    public ResponseEntity<?> updateAssignment(@PathVariable int assignmentId, @RequestBody AssignmentDto assignmentDto) {
        return ResponseEntity.ok(assignmentService.updateAssignment(assignmentId, assignmentDto));
    }

    @DeleteMapping("/delete/{assignmentId}")
    public ResponseEntity<?> deleteAssignment(@PathVariable int assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
        return ResponseEntity.noContent().build();
    }
}
