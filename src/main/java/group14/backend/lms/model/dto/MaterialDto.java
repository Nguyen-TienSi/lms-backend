package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Material;
import lombok.Builder;

@Builder
public record MaterialDto(
        Long id,
        String name,
        String url,
        byte[] fileData,
        String fileName,
        String fileType,
        Long courseId
) {
    public static MaterialDto convertToDto(Material material) {
        return MaterialDto.builder()
                .id(material.getId())
                .name(material.getName())
                .url(material.getUrl())
                .fileData(material.getFileData())
                .fileName(material.getFileName())
                .fileType(material.getFileType())
                .courseId(material.getCourse().getId())
                .build();
    }
}
