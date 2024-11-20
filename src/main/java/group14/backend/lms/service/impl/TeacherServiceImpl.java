package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Course;
import group14.backend.lms.model.entity.Role;
import group14.backend.lms.model.entity.Subject;
import group14.backend.lms.model.entity.Teacher;
import group14.backend.lms.model.dto.TeacherDto;
import group14.backend.lms.repository.ICourseRepository;
import group14.backend.lms.repository.IRoleRepository;
import group14.backend.lms.repository.ISubjectRepository;
import group14.backend.lms.repository.ITeacherRepository;
import group14.backend.lms.service.ITeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeacherServiceImpl implements ITeacherService {
    private final ITeacherRepository teacherRepository;
    private final ICourseRepository courseRepository;
    private final ISubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;
    private final IRoleRepository roleRepository;

    public List<TeacherDto> getAllTeachers() {
        return ((List<Teacher>) teacherRepository.findAll())
                .stream()
                .map(TeacherDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();

        teacher.setFirstName(teacherDto.userDto().firstName());
        teacher.setLastName(teacherDto.userDto().lastName());
        teacher.setGender(teacherDto.userDto().gender());
        teacher.setBirthDate(teacherDto.userDto().birthDate());
        teacher.setPhone(teacherDto.userDto().phone());
        teacher.setUsername(teacherDto.userDto().username());
        teacher.setPassword(passwordEncoder.encode("password"));
        teacher.setAuthorities(roleRepository.findByAuthority("ROLE_TEACHER")
                .stream()
                .collect(Collectors.toSet()));

        Set<Subject> subjects = new HashSet<>((Collection<Subject>) subjectRepository.findAllById(teacherDto.subjectIds()));

        teacher.setSubjects(subjects);

        subjects.forEach(subject -> subject.getTeachers().add(teacher));

        return TeacherDto.convertToDto(teacherRepository.save(teacher));
    }

    @Override
    public TeacherDto getTeacherById(long id) {
        return teacherRepository.findById(id)
                .map(TeacherDto::convertToDto)
                .orElseThrow();
    }

    @Override
    public TeacherDto updateTeacher(long id, TeacherDto teacherDto) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow();

        // Update basic user details
        teacher.setFirstName(teacherDto.userDto().firstName());
        teacher.setLastName(teacherDto.userDto().lastName());
        teacher.setGender(teacherDto.userDto().gender());
        teacher.setBirthDate(teacherDto.userDto().birthDate());
        teacher.setPhone(teacherDto.userDto().phone());

        // Update subjects
        Set<Subject> currentSubjects = teacher.getSubjects();
        Set<Subject> newSubjects = new HashSet<>((Collection<Subject>) subjectRepository.findAllById(teacherDto.subjectIds()));
        currentSubjects.forEach(subject -> subject.getTeachers().remove(teacher));
        newSubjects.forEach(subject -> subject.getTeachers().add(teacher));
        teacher.setSubjects(newSubjects);

        // Update courses TODO update teacher course
        Set<Course> currentCourses = new HashSet<>(teacher.getCourses());
        Set<Course> newCourses = new HashSet<>((Collection<Course>) courseRepository.findAllById(teacherDto.courseIds()));
        currentCourses.stream()
                .filter(course -> !newCourses.contains(course))
                .forEach(course -> {
                    teacher.getCourses().remove(course);
                    course.setTeacher(null);
                });
        newCourses.stream()
                .filter(course -> !currentCourses.contains(course))
                .forEach(course -> {
                    teacher.getCourses().add(course);
                    course.setTeacher(teacher);
                });

        return TeacherDto.convertToDto(teacherRepository.save(teacher));
    }

}
