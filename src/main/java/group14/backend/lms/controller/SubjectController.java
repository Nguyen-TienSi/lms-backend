package group14.backend.lms.controller;

import group14.backend.lms.model.dto.SubjectDto;
import group14.backend.lms.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @GetMapping("")
    public ResponseEntity<?> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<?> getSubjectById(@PathVariable int subjectId) {
        return ResponseEntity.ok(subjectService.getSubjectById(subjectId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSubject(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.createSubject(subjectDto));
    }

    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable int subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }
}
