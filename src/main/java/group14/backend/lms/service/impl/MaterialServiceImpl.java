package group14.backend.lms.service.impl;

import group14.backend.lms.model.dto.MaterialDto;
import group14.backend.lms.model.entity.Material;
import group14.backend.lms.repository.ICourseRepository;
import group14.backend.lms.repository.IMaterialRepository;
import group14.backend.lms.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MaterialServiceImpl implements IMaterialService {
    private final IMaterialRepository materialRepository;
    private final ICourseRepository courseRepository;
    private final Path uploadLocation;

    @Autowired
    public MaterialServiceImpl(
            IMaterialRepository materialRepository,
            ICourseRepository courseRepository,
            @Value("${upload.dir}") String uploadDir) {
        this.materialRepository = materialRepository;
        this.courseRepository = courseRepository;
        this.uploadLocation = Paths.get(uploadDir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.uploadLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MaterialDto> getMaterialByCourseId(long courseId) {
        List<Material> materialList = materialRepository.findAllByCourseId(courseId);
        return materialList.stream()
                .map(MaterialDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MaterialDto uploadMaterial(String name, String url, MultipartFile fileData, long courseId) throws IOException {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(fileData.getOriginalFilename()));
        Path targetLocation = this.uploadLocation.resolve(originalFileName);
        Files.copy(fileData.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        Material material = new Material();
        material.setName(name);
        material.setUrl(url);
        material.setFileData(fileData.getBytes());
        material.setFileName(originalFileName);
        material.setFileType(fileData.getContentType());
        material.setCourse(courseRepository.findById(courseId).orElseThrow());

        return MaterialDto.convertToDto(materialRepository.save(material));
    }

    @Override
    public MaterialDto downloadMaterial(long id) throws IOException {
        return MaterialDto.convertToDto(materialRepository.findById(id).orElseThrow());
    }

    @Override
    public void deleteMaterial(long id) throws IOException {
        materialRepository.findById(id).ifPresent(materialRepository::delete);
    }
}
