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
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        enum Status {
            AUTHENTICATED, UNAUTHENTICATED
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String role = "";
            if (authentication.getPrincipal() instanceof User user) {
                role = user.getAuthorities()
                        .stream()
                        .findFirst()
                        .map(GrantedAuthority::getAuthority)
                        .orElseThrow();
            }
            return new LoginResponseDto(Status.AUTHENTICATED.name(), "Login successful", role);
        } catch (BadCredentialsException e) {
            return new LoginResponseDto(Status.UNAUTHENTICATED.name(), "Incorrect username or password", null);
        } catch (RuntimeException e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }

    @Override
    public UserDto register(UserRegistrationDto registrationDto) {
        User user = userRepository.findByUsername(registrationDto.username()).orElseThrow();

        Role role = roleRepository.findByAuthority(registrationDto.authority()).orElseThrow();
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        if (Objects.equals(role.getAuthority(), "ROLE_TEACHER")) {
            user = new Teacher();
        } else if (Objects.equals(role.getAuthority(), "ROLE_STUDENT")) {
            user = new Student();
        }

        user.setFirstName(registrationDto.firstName());
        user.setLastName(registrationDto.lastName());
        user.setGender(registrationDto.gender());
        user.setBirthDate(registrationDto.birthDate());
        user.setPhone(registrationDto.phone());
        user.setUsername(registrationDto.username());
        user.setPassword(passwordEncoder.encode(registrationDto.password()));
        user.setAuthorities(roles);

        return UserDto.convertToDto(userRepository.save(user));
    }
}
