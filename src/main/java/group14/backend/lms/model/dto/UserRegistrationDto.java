package group14.backend.lms.model.dto;

import java.util.Date;

public record UserRegistrationDto (
        String firstName,
        String lastName,
        Boolean gender,
        Date birthDate,
        String phone,
        String authority,
        String username,
        String password
) {}
