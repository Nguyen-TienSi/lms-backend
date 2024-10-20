package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String question;

    @ManyToOne
    @JoinColumn(name = "assignment_fk")
    private Assignment assignment;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question", orphanRemoval = true)
    private List<Answer> answer = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "correct_answer_fk")
    private Answer correctAnswer;
}
