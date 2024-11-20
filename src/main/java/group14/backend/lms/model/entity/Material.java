package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    @Lob
    private byte[] fileData;

    private String fileName;

    private String fileType;

    @ManyToOne
    @JoinColumn(name = "course_fk")
    private Course course;
}
