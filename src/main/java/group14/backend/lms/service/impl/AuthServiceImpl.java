package group14.backend.lms.service.impl;

import group14.backend.lms.model.dto.LoginResponseDto;
import group14.backend.lms.model.dto.UserDto;
import group14.backend.lms.model.entity.Role;
import group14.backend.lms.model.entity.Student;
import group14.backend.lms.model.entity.Teacher;
import group14.backend.lms.model.entity.User;
import group14.backend.lms.model.dto.LoginRequestDto;
import group14.backend.lms.model.dto.UserRegistrationDto;
import group14.backend.lms.repository.IRoleRepository;
import group14.backend.lms.repository.IUserRepository;
import group14.backend.lms.service.IAuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final IUserRepository _userRepository;
    private final IRoleRepository _roleRepository;
    private final PasswordEncoder _passwordEncoder;
    private final AuthenticationManager _authenticationManager;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        enum Status {
            AUTHENTICATED, UNAUTHENTICATED
        }

        try {
            Authentication authentication = _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = (User) authentication.getPrincipal();
            String role = user != null ? user.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null) : null;
            return new LoginResponseDto(Status.AUTHENTICATED.name(), "Login successful", role);
        } catch (BadCredentialsException e) {
            return new LoginResponseDto(Status.UNAUTHENTICATED.name(), "Incorrect username or password", null);
        } catch (RuntimeException e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDto register(UserRegistrationDto registrationDto) {
        User user = _userRepository.findByUsername(registrationDto.username()).orElse(null);
        if (user != null) {
            throw new RuntimeException("Username already in use");
        }

        Role role = _roleRepository.findByAuthority(registrationDto.authority()).orElseThrow(() -> new RuntimeException("Role not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        if (Objects.equals(role.getAuthority(), "ROLE_TEACHER")) {
            user = new Teacher();
        } else if (Objects.equals(role.getAuthority(), "ROLE_STUDENT")) {
            user = new Student();
        }

        assert user != null;
        user.setFirstName(registrationDto.firstName());
        user.setLastName(registrationDto.lastName());
        user.setGender(registrationDto.gender());
        user.setBirthDate(registrationDto.birthDate());
        user.setPhone(registrationDto.phone());
        user.setUsername(registrationDto.username());
        user.setPassword(_passwordEncoder.encode(registrationDto.password()));
        user.setAuthorities(roles);

        return UserDto.convertToDto(_userRepository.save(user));
    }
}
