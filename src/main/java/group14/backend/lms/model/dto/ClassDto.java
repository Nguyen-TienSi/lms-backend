package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.Class;

public record ClassDto(
        long id,
        String name
) {
    public static ClassDto convertToDto(Class aClass) {
        return new ClassDto(aClass.getId(), aClass.getName());
    }
}
