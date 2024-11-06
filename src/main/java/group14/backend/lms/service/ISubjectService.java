package group14.backend.lms.service;

import group14.backend.lms.model.dto.SubjectDto;

import java.util.List;

public interface ISubjectService {
    List<SubjectDto> getAllSubjects();
    SubjectDto createSubject(SubjectDto subjectDto);
    void deleteSubject(long subjectId);
}
