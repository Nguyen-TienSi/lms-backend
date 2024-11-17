package group14.backend.lms.service;

import group14.backend.lms.model.dto.ClassDto;

import java.util.List;

public interface IClassService {
    List<ClassDto> getAllClasses();
    ClassDto getClassById(long id);
    ClassDto addClass(ClassDto classDto);
    void deleteClass(long classId);
}
