package group14.backend.lms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Teacher extends User {
    //    @JsonIgnoreProperties("teachers")
    @JsonIgnore
    @ManyToMany(mappedBy = "teachers")
    private Set<Subject> subjects = new HashSet<>();

    //    @JsonIgnoreProperties("teacher")
    @JsonIgnore
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses = new HashSet<>();
}
