package group14.backend.lms.controller;

import group14.backend.lms.model.dto.SubmissionDto;
import group14.backend.lms.model.entity.Student;
import group14.backend.lms.model.entity.Teacher;
import group14.backend.lms.service.ISubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    @Autowired
    private ISubmissionService submissionService;

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<?> getSubmissionByAssignmentId(@PathVariable int assignmentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof Student student) {
            return ResponseEntity.ok(submissionService.getSubmissionByAssignmentIdAndStudentId(assignmentId, student.getId()));
        }
        return ResponseEntity.ok(submissionService.getSubmissionsByAssignmentId(assignmentId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getSubmissionByStudentId(@PathVariable int studentId) {
        return ResponseEntity.ok(submissionService.getSubmissionByStudentId(studentId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSubmission(@RequestBody SubmissionDto submissionDto) {
        return ResponseEntity.ok(submissionService.createSubmission(submissionDto));
    }
}
