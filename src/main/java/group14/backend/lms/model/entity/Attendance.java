package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_fk", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_fk", referencedColumnName = "id")
    private Room room;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime attendanceTime;

    public enum Status {
        NONE, PUNCTUAL, LATE, ABSENT
    }
}
