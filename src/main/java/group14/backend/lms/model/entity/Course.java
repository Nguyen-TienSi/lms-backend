package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_fk", referencedColumnName = "id")
    private Class aClass;

    @ManyToOne
    @JoinColumn(name = "subject_fk", referencedColumnName = "id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_fk", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "semester_fk", referencedColumnName = "id")
    private Semester semester;

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_fk"),
            inverseJoinColumns = @JoinColumn(name = "student_fk")
    )
    private Set<Student> students = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course", orphanRemoval = true)
    private Set<Assignment> assignments = new HashSet<>();
}
