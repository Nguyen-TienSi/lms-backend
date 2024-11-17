package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_fk", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "assignment_fk", referencedColumnName = "id")
    private Assignment assignment;

    @Lob
    private byte[] fileData;

    @ManyToMany
    @JoinTable(
            name = "submission_answer",
            joinColumns = @JoinColumn(name = "submission_fk"),
            inverseJoinColumns = @JoinColumn(name = "answer_fk")
    )
    private List<Answer> answers = new ArrayList<>();

    private LocalDateTime submissionTime;  // Thời gian nộp bài
}
