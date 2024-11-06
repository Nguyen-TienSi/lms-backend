package group14.backend.lms.controller;

import group14.backend.lms.model.dto.QuestionDto;
import group14.backend.lms.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<?> getQuestionsByAssignmentId(@PathVariable int assignmentId) {
        return ResponseEntity.ok(questionService.getQuestionsByAssignmentId(assignmentId));
    }

    @PostMapping("/add")
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(questionService.createQuestion(questionDto));
    }

    @PutMapping("/update/{questionId}")
    public ResponseEntity<?> updateQuestion(@PathVariable int questionId, @RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(questionService.updateQuestion(questionId, questionDto));
    }

    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable int questionId) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}
