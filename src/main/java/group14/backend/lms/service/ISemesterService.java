package group14.backend.lms.service;

import group14.backend.lms.model.dto.SemesterDto;

import java.util.List;

public interface ISemesterService {
    List<SemesterDto> getAllSemesters();
    SemesterDto getSemesterById(long id);
    SemesterDto addSemester(SemesterDto semester);
    void deleteSemester(long id);
}
