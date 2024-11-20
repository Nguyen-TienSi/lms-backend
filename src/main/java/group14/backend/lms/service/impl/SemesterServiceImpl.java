package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Semester;
import group14.backend.lms.model.dto.SemesterDto;
import group14.backend.lms.repository.ISemesterRepository;
import group14.backend.lms.service.ISemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements ISemesterService {
    @Autowired
    private ISemesterRepository semesterRepository;

    @Override
    public List<SemesterDto> getAllSemesters() {
        List<Semester> semesters = (List<Semester>) semesterRepository.findAll();
        return semesters.stream()
                .map(SemesterDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SemesterDto getSemesterById(long id) {
        return SemesterDto.convertToDto(semesterRepository.findById(id).orElseThrow());
    }

    @Override
    public SemesterDto addSemester(SemesterDto semesterDto) {
        if (semesterRepository.findByName(semesterDto.name()).isPresent()) {
            throw new IllegalArgumentException("A semester with the name " + semesterDto.name() + " already exists.");
        }
        Semester semester = new Semester();
        semester.setName(semesterDto.name());
        semester.setStartDate(semesterDto.startDate());
        semester.setEndDate(semesterDto.endDate());
        return SemesterDto.convertToDto(semesterRepository.save(semester));
    }

    @Override
    public void deleteSemester(long id) {
        semesterRepository.findById(id).ifPresent(semesterRepository::delete);
    }
}
