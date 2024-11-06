package group14.backend.lms.model.dto;

import group14.backend.lms.model.entity.User;
import lombok.*;

import java.util.Date;

@Builder
public record UserDto(
        Long id,
        String role,
        String firstName,
        String lastName,
        Boolean gender,
        Date birthDate,
        String username,
        String phone
) {
    public static UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .role(user.getAuthorities().iterator().next().getAuthority())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .username(user.getUsername())
                .phone(user.getPhone())
                .build();
    }
}
