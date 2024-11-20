package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.*;
import group14.backend.lms.model.entity.Class;
import group14.backend.lms.repository.*;
import group14.backend.lms.service.IDataInitializerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DataInitializerServiceImpl implements IDataInitializerService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final ISemesterRepository semesterRepository;
    private final IClassRepository classRepository;
    private final ISubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void init() {
        createUsers();
        createSemesters();
        createClasses();
        createSubjects();
    }

    public void createUsers() {
        createRoleIfNotExist("ROLE_STUDENT");
        createRoleIfNotExist("ROLE_TEACHER");
        createRoleIfNotExist("ROLE_ADMIN");

        createUserIfNotExist(
                "Admin", "1",
                "admin1@example.com",
                "ROLE_ADMIN"
        );
        for (int i = 1; i <= 5; i++) {
            createUserIfNotExist(
                    "Student", String.valueOf(i),
                    "student" + i + "@example.com",
                    "ROLE_STUDENT"
            );
            createUserIfNotExist(
                    "Teacher", String.valueOf(i),
                    "teacher" + i + "@example.com",
                    "ROLE_TEACHER"
            );
        }
    }

    @Override
    public void createSemesters() {
        for (int i = 1; i <= 5; i++) {
            Semester semester = new Semester();
            semester.setName("Semester " + i);
            semester.setStartDate(LocalDate.now());
            semester.setEndDate(LocalDate.now().plusDays(i));
            if (semesterRepository.findByName(semester.getName()).isEmpty()) {
                semesterRepository.save(semester);
            }
        }
    }

    @Override
    public void createClasses() {
        for (int i = 1; i <= 5; i++) {
            Class aClass = new Class();
            aClass.setName("Class " + i);
            if (classRepository.findByName(aClass.getName()).isEmpty()) {
                classRepository.save(aClass);
            }
        }
    }

    @Override
    public void createSubjects() {
        for (int i = 1; i <= 5; i++) {
            Subject subject = new Subject();
            subject.setName("Subject " + i);
            if (subjectRepository.findByName(subject.getName()).isEmpty()) {
                subjectRepository.save(subject);
            }
        }
    }

    private void createRoleIfNotExist(String authority) {
        if (roleRepository.findByAuthority(authority).isEmpty()) {
            Role role = new Role(authority);
            roleRepository.save(role);
        }
    }

    private void createUserIfNotExist(
            String firstName, String lastName,
            String username, String... roles
    ) {
        if (userRepository.findByUsername(username).isEmpty()) {
            Set<Role> authorities = new HashSet<>();
            for (String roleName : roles) {
                Role role = roleRepository.findByAuthority(roleName).orElseThrow();
                authorities.add(role);
            }

            User user;
            if (roles.length == 1 && roles[0].equals("ROLE_STUDENT")) {
                user = new Student();
            } else if (roles.length == 1 && roles[0].equals("ROLE_TEACHER")) {
                user = new Teacher();
            } else {
                user = new Admin();
            }

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setGender(true);
            user.setBirthDate(new Date());
            user.setPhone("123");
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode("password"));
            user.setAuthorities(authorities);
            userRepository.save(user);
        }
    }
}
