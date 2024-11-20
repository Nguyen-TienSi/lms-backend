package group14.backend.lms.controller;

import group14.backend.lms.model.dto.AnswerDto;
import group14.backend.lms.service.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    @Autowired
    private IAnswerService answerService;

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<?> getAnswerByAssignmentId(@PathVariable int assignmentId) {
        return ResponseEntity.ok(answerService.getAnswerByAssignmentId(assignmentId));
    }

    @PutMapping("/update/{answerId}")
    public ResponseEntity<?> updateAnswer(@PathVariable int answerId, @RequestBody AnswerDto answerDto) {
        return ResponseEntity.ok(answerService.updateAnswer(answerId, answerDto));
    }

    @DeleteMapping("/delete/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable int answerId) {
        answerService.deleteAnswer(answerId);
        return ResponseEntity.noContent().build();
    }
}
