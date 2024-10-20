package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Semester {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
