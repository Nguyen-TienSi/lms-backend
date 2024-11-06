package group14.backend.lms.service;

import group14.backend.lms.model.dto.CourseDto;

import java.util.List;

public interface ICourseService {
    List<CourseDto> getAllCourses();
    CourseDto getCourseById(long id);
    List<CourseDto> getCourseByTeacherId(long id);
    List<CourseDto> getCourseByStudentId(long id);
    CourseDto createCourse(CourseDto courseDto);
    CourseDto updateCourse(long id, CourseDto courseDto);
    void deleteCourse(long id);
}
