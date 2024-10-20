package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;

    private Integer attendance;

    @ManyToOne
    @JoinColumn(name = "course_fk")
    private Course course;
}

enum Status {
    NONE, PUNCTUAL, LATE, ABSENT
}