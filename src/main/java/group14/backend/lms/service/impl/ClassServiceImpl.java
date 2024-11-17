package group14.backend.lms.service.impl;

import group14.backend.lms.model.entity.Class;
import group14.backend.lms.model.dto.ClassDto;
import group14.backend.lms.repository.IClassRepository;
import group14.backend.lms.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements IClassService {
    @Autowired
    private IClassRepository classRepository;

    @Override
    public List<ClassDto> getAllClasses() {
        List<Class> classes = (List<Class>) classRepository.findAll();
        return classes.stream()
                .map(ClassDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassDto getClassById(long id) {
        return ClassDto.convertToDto(classRepository.findById(id).orElseThrow());
    }

    @Override
    public ClassDto addClass(ClassDto classDto) {
        if (classRepository.findByName(classDto.name()).isPresent()) {
            throw new IllegalArgumentException("Class name already exists");
        }
        Class newClass = new Class();
        newClass.setName(classDto.name());

        return ClassDto.convertToDto(classRepository.save(newClass));
    }

    @Override
    public void deleteClass(long classId) {
        Class aClass = classRepository.findById(classId).orElseThrow();
        classRepository.delete(aClass);
    }
}
