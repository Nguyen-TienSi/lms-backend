package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "question_fk")
    private Question question;
}
