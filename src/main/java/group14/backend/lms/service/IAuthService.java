package group14.backend.lms.service;

import group14.backend.lms.model.dto.LoginResponseDto;
import group14.backend.lms.model.dto.UserDto;
import group14.backend.lms.model.dto.LoginRequestDto;
import group14.backend.lms.model.dto.UserRegistrationDto;

public interface IAuthService {
    LoginResponseDto login(LoginRequestDto loginForm);
    UserDto register(UserRegistrationDto registrationDto);
}
