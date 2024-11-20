package group14.backend.lms.controller;

import group14.backend.lms.model.dto.MaterialDto;
import group14.backend.lms.service.IMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {
    @Autowired
    private IMaterialService materialService;

    @GetMapping("/course/{id}")
    public ResponseEntity<?> getMaterialByCourseId(@PathVariable int id) {
        return ResponseEntity.ok(materialService.getMaterialByCourseId(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> uploadMaterial(
            @RequestParam("name") String name,
            @RequestParam("url") String url,
            @RequestParam("fileData") MultipartFile fileData,
            @RequestParam("courseId") int courseId
    ) throws IOException {
        return ResponseEntity.ok(materialService.uploadMaterial(name, url, fileData, courseId));
    }

    @GetMapping("/download/{materialId}")
    public ResponseEntity<Resource> downloadMaterial(@PathVariable int materialId) throws IOException {
        MaterialDto materialDto = materialService.downloadMaterial(materialId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(materialDto.fileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "material; filename=\"" + materialDto.fileName() + "\"")
                .body(new ByteArrayResource(materialDto.fileData()));
    }

    @DeleteMapping("/delete/{materialId}")
    public ResponseEntity<?> deleteMaterial(@PathVariable int materialId) throws IOException {
        materialService.deleteMaterial(materialId);
        return ResponseEntity.noContent().build();
    }
}
