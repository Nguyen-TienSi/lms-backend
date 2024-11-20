package group14.backend.lms.service;

import group14.backend.lms.model.dto.MaterialDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMaterialService {
    List<MaterialDto> getMaterialByCourseId(long courseId);
    MaterialDto uploadMaterial(String name, String url, MultipartFile fileData, long courseId) throws IOException;
    MaterialDto downloadMaterial(long id) throws IOException;
    void deleteMaterial(long id) throws IOException;
}
