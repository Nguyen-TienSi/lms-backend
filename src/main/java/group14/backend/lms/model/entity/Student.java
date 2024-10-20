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
public class Student extends Account {
    public Student() {
        setRole(Role.STUDENT);
    }

    @OneToOne
    @JoinColumn(name = "class_fk")
    private Class aClass;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_fk"),
            inverseJoinColumns = @JoinColumn(name = "course_fk")
    )
    private Set<Course> courses = new HashSet<>();
}
