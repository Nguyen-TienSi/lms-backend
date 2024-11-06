package group14.backend.lms.model.dto;

public record LoginResponseDto(
        String status,
        String message,
        String role
) {
}
