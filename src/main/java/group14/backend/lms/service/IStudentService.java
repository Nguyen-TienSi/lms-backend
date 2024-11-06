package group14.backend.lms.service;

import group14.backend.lms.model.dto.StudentDto;

import java.util.List;

public interface IStudentService {
    List<StudentDto> getAllStudents();
    StudentDto createStudent(StudentDto studentDto);
    StudentDto getStudentById(long id);
    StudentDto updateStudent(long id, StudentDto studentDto);
}
