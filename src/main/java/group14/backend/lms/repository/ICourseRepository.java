package group14.backend.lms.repository;

import group14.backend.lms.model.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICourseRepository extends CrudRepository<Course, Long> {
//    @Query("SELECT c FROM Course c JOIN c.teacher t WHERE t.id = :teacherId")
    List<Course> findCoursesByTeacherId(long teacherId);
    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    List<Course> findCoursesByStudentId(long studentId);
}
