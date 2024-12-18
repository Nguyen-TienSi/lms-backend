package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Subject;
import group14.backend.lms.model.dto.SubjectDto;
import group14.backend.lms.repository.ISubjectRepository;
import group14.backend.lms.repository.ITeacherRepository;
import group14.backend.lms.service.ISubjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements ISubjectService {
    private final ISubjectRepository subjectRepository;
    private final ITeacherRepository teacherRepository;

    @Override
    public List<SubjectDto> getAllSubjects() {
        List<Subject> subjectList = (List<Subject>) subjectRepository.findAll();
        return subjectList.stream()
                .map(SubjectDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto getSubjectById(long id) {
        return SubjectDto.convertToDto(subjectRepository.findById(id).orElseThrow());
    }

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.name());

        return SubjectDto.convertToDto(subjectRepository.save(subject));
    }

    @Override
    public void deleteSubject(long subjectId) {
        subjectRepository.findById(subjectId).ifPresent(subjectRepository::delete);
    }
}
