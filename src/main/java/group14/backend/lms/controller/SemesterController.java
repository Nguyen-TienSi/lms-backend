package group14.backend.lms.controller;

import group14.backend.lms.model.dto.SemesterDto;
import group14.backend.lms.service.ISemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/semesters")
public class SemesterController {
    @Autowired
    private ISemesterService semesterService;

    @GetMapping("")
    private ResponseEntity<?> getAllSemesters() {
        return ResponseEntity.ok(semesterService.getAllSemesters());
    }

    @PostMapping("/add")
    private ResponseEntity<?> addSemester(@RequestBody SemesterDto semesterDto) {
        return ResponseEntity.ok(semesterService.addSemester(semesterDto));
    }

    @DeleteMapping("/delete/{semesterId}")
    private ResponseEntity<?> deleteSemester(@PathVariable int semesterId) {
        semesterService.deleteSemester(semesterId);
        return ResponseEntity.noContent().build();
    }
}
