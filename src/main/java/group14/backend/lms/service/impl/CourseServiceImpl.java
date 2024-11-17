package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.*;
import group14.backend.lms.model.dto.CourseDto;
import group14.backend.lms.repository.*;
import group14.backend.lms.service.ICourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements ICourseService {
    private final ICourseRepository courseRepository;
    private final IClassRepository classRepository;
    private final ISubjectRepository subjectRepository;
    private final ITeacherRepository teacherRepository;
    private final ISemesterRepository semesterRepository;
    private final IStudentRepository studentRepository;
    private final IRoomRepository roomRepository;
    private final IAssignmentRepository assignmentRepository;

    @Override
    public List<CourseDto> getAllCourses() {
        List<Course> courses = (List<Course>) courseRepository.findAll();
        return courses.stream()
                .map(CourseDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto getCourseById(long id) {
        return courseRepository.findById(id)
                .map(CourseDto::convertToDto)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
    }

    @Override
    public List<CourseDto> getCourseByUserId(long id) {
        return courseRepository.findCoursesByUserId(id).stream()
                .map(CourseDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setAClass(classRepository.findById(courseDto.classId()).orElseThrow());
        course.setSubject(subjectRepository.findById(courseDto.subjectId()).orElseThrow());
        course.setSemester(semesterRepository.findById(courseDto.semesterId()).orElseThrow());
        Teacher teacher = teacherRepository.findById(courseDto.teacherId()).orElseThrow();
        course.setTeacher(teacher);

        Set<Student> students = new HashSet<>((Collection<Student>) studentRepository.findAllById(courseDto.studentIds()));
        students.forEach(student -> student.getCourses().add(course));
        course.setStudents(students);
        teacher.getCourses().add(course);

        return CourseDto.convertToDto(courseRepository.save(course));
    }

    @Override
    public CourseDto updateCourse(long id, CourseDto courseDto) {
        Course course = courseRepository.findById(id).orElseThrow();

        course.setAClass(classRepository.findById(courseDto.classId()).orElseThrow());
        course.setSubject(subjectRepository.findById(courseDto.subjectId()).orElseThrow());
        course.setSemester(semesterRepository.findById(courseDto.semesterId()).orElseThrow());
        course.setTeacher(teacherRepository.findById(courseDto.teacherId()).orElseThrow());

        // Efficiently update student sets - CORRECTED
        Set<Student> currentStudents = new HashSet<>(course.getStudents()); //Store existing students
        Set<Student> newStudents = new HashSet<>((Collection<Student>) studentRepository.findAllById(courseDto.studentIds()));

        //Remove students that are no longer in the course
        currentStudents.removeAll(newStudents);
        currentStudents.forEach(student -> student.getCourses().remove(course));

        //Add new students to the course
        newStudents.removeAll(currentStudents); //Remove already existing students
        newStudents.forEach(student -> student.getCourses().add(course));

        course.setStudents(newStudents); //Update the course's student set

        // Efficiently update room sets
        course.getRooms().clear();
        course.getRooms().addAll(new HashSet<>((Collection<Room>) roomRepository.findAllById(courseDto.roomIds())));

        // Efficiently update assignment sets - CORRECTED
        course.getAssignments().clear();
        course.getAssignments().addAll(new HashSet<>((Collection<Assignment>) assignmentRepository.findAllById(courseDto.assignmentIds())));

        return CourseDto.convertToDto(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
        courseRepository.delete(course);
    }
}
