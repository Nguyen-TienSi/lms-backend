package group14.backend.lms;

import group14.backend.lms.model.entity.Account;
import group14.backend.lms.model.entity.Admin;
import group14.backend.lms.model.entity.Student;
import group14.backend.lms.model.entity.Teacher;
import group14.backend.lms.repository.IAccountRepository;
import group14.backend.lms.repository.ISystemAdminRepository;
import group14.backend.lms.repository.IStudentRepository;
import group14.backend.lms.repository.ITeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LmsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LmsApplication.class, args);
        IAccountRepository studentRepository = applicationContext.getBean(IStudentRepository.class);
        IAccountRepository teacherRepository = applicationContext.getBean(ITeacherRepository.class);
        IAccountRepository adminRepository = applicationContext.getBean(ISystemAdminRepository.class);

        Account student = new Student();
        Account teacher = new Teacher();
        Account administrator = new Admin();

        studentRepository.save(student);
        teacherRepository.save(teacher);
        adminRepository.save(administrator);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
