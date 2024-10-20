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
public class Teacher extends Account {
    public Teacher() {
        setRole(Role.TEACHER);
    }

    @ManyToMany(mappedBy = "teachers")
    private Set<Subject> subjects = new HashSet<>();
}
