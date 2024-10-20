package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "class_fk")
    private Class aClass;

    @OneToOne
    @JoinColumn(name = "subject_fk")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "teacher_fk")
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "semester_fk")
    private Semester semester;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    private Set<Assignment> assignments = new HashSet<>();
}
