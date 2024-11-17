package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.*;
import group14.backend.lms.model.dto.StudentDto;
import group14.backend.lms.repository.*;
import group14.backend.lms.service.IStudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentServiceImpl implements IStudentService {
    private final IStudentRepository studentRepository;
    private final IClassRepository classRepository;
    private final ICourseRepository courseRepository;
    private final IAttendanceRepository attendanceRepository;
    private final ISubmissionRepository submissionRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<StudentDto> getAllStudents() {
        return ((List<Student>) studentRepository.findAll()).stream()
                .map(StudentDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = new Student();

        student.setFirstName(studentDto.userDto().firstName());
        student.setLastName(studentDto.userDto().lastName());
        student.setGender(studentDto.userDto().gender());
        student.setBirthDate(studentDto.userDto().birthDate());
        student.setPhone(studentDto.userDto().phone());
        student.setUsername(studentDto.userDto().username());
        student.setPassword(passwordEncoder.encode("password"));
        student.setAClass(classRepository.findById(studentDto.classId()).orElseThrow());
        student.setAuthorities(roleRepository.findByAuthority(studentDto.userDto().role())
                .stream()
                .collect(Collectors.toSet())
        );

        return StudentDto.convertToDto(studentRepository.save(student));
    }

    @Override
    public StudentDto getStudentById(long id) {
        return StudentDto.convertToDto(studentRepository.findById(id).orElseThrow());
    }

    @Override
    public StudentDto updateStudent(long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id).orElseThrow();

        // Basic user details
        student.setFirstName(studentDto.userDto().firstName());
        student.setLastName(studentDto.userDto().lastName());
        student.setGender(studentDto.userDto().gender());
        student.setBirthDate(studentDto.userDto().birthDate());
        student.setPhone(studentDto.userDto().phone());

        // Update class
        student.setAClass(classRepository.findById(studentDto.classId()).orElseThrow());

        // Update courses
        Set<Course> currentCourses = student.getCourses();
        Set<Course> newCourses = new HashSet<>((List<Course>) courseRepository.findAllById(studentDto.courseIds()));
        currentCourses.forEach(course -> course.getStudents().remove(student));
        newCourses.forEach(course -> course.getStudents().add(student));
        currentCourses.clear();
        currentCourses.addAll(newCourses);

        // Update attendances
        Set<Attendance> currentAttendances = student.getAttendances();
        Set<Attendance> newAttendances = new HashSet<>((List<Attendance>) attendanceRepository.findAllById(studentDto.attendanceIds()));
        newAttendances.forEach(attendance -> attendance.setStudent(student));
        currentAttendances.clear();
        currentAttendances.addAll(newAttendances);

        // Update submissions
        Set<Submission> currentSubmissions = student.getSubmissions();
        Set<Submission> newSubmissions = new HashSet<>((List<Submission>) submissionRepository.findAllById(studentDto.submissionIds()));
        newSubmissions.forEach(submission -> submission.setStudent(student));
        currentSubmissions.clear();
        currentSubmissions.addAll(newSubmissions);

        return StudentDto.convertToDto(studentRepository.save(student));
    }
}
