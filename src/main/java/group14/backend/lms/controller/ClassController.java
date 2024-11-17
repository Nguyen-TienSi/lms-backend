package group14.backend.lms.controller;

import group14.backend.lms.model.dto.ClassDto;
import group14.backend.lms.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
public class ClassController {
    @Autowired
    private IClassService classService;

    @GetMapping("")
    public ResponseEntity<?> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }

    @GetMapping("/{classId}")
    public ResponseEntity<?> getClassById(@PathVariable int classId) {
        return ResponseEntity.ok(classService.getClassById(classId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addClass(@RequestBody ClassDto classDto) {
        return ResponseEntity.ok(classService.addClass(classDto));
    }

    @DeleteMapping("/delete/{classId}")
    public ResponseEntity<?> deleteClass(@PathVariable int classId) {
        classService.deleteClass(classId);
        return ResponseEntity.noContent().build();
    }
}
