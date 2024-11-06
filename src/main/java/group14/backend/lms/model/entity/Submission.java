package group14.backend.lms.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_fk", referencedColumnName = "id")
    private Student student;  // Sinh viên nộp bài

    @ManyToOne
    @JoinColumn(name = "assignment_fk", referencedColumnName = "id")
    private Assignment assignment;  // Bài tập mà sinh viên nộp bài

    private String fileName;  // Tên file mà sinh viên nộp

    private String fileType;  // Loại file (ví dụ: pdf, docx)

    @Lob
    private byte[] fileData;  // Dữ liệu file dưới dạng byte[]

    @ManyToMany
    @JoinTable(
            name = "submission_answer",
            joinColumns = @JoinColumn(name = "submission_fk"),
            inverseJoinColumns = @JoinColumn(name = "answer_fk")
    )
    private List<Answer> answers = new ArrayList<>();

    private LocalDateTime submissionTime;  // Thời gian nộp bài
}
