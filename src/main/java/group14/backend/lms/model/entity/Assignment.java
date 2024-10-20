package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Assignment {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalTime duration;

    @Lob
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "course_fk")
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignment", orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();
}
