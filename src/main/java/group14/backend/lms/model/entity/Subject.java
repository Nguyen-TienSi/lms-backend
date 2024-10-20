package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Subject {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(name = "subject_fk"),
            inverseJoinColumns = @JoinColumn(name = "teacher_fk")
    )
    private Set<Teacher> teachers = new HashSet<>();
}