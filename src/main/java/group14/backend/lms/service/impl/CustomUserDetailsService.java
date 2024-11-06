package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Student;
import group14.backend.lms.model.entity.Teacher;
import group14.backend.lms.model.entity.User;
import group14.backend.lms.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;
    private final ITeacherRepository teacherRepository;
    private final IStudentRepository studentRepository;
    private final ICourseRepository courseRepository;
    private final ISubjectRepository subjectRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            if (u instanceof Teacher teacher) {
                teacher.getSubjects().forEach(subject -> {
                    subject.getTeachers().remove(teacher);
                    subjectRepository.save(subject);
                });
                teacherRepository.delete(teacher);
            } else if (u instanceof Student student) {
                student.getCourses().forEach(course -> {
                    course.getStudents().remove(student);
                    courseRepository.save(course);
                });
                student.getAttendances().clear();
                student.getSubmissions().clear();
                studentRepository.delete(student);
            }
        }
    }
}
