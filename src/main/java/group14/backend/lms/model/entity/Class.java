package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Class {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
