package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Duration duration;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "course_fk", referencedColumnName = "id")
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignment", orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assignment", orphanRemoval = true)
    private List<Submission> submissions = new ArrayList<>();
}
