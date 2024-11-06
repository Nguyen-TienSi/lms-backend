package group14.backend.lms.service;

import group14.backend.lms.model.dto.TeacherDto;

import java.util.List;

public interface ITeacherService {
    List<TeacherDto> getAllTeachers();
    TeacherDto createTeacher(TeacherDto teacherDto);
    TeacherDto getTeacherById(long id);
    TeacherDto updateTeacher(long id, TeacherDto teacherDto);
}
