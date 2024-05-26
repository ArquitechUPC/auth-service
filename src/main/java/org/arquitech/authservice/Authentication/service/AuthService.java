package org.arquitech.authservice.Authentication.service;

import lombok.RequiredArgsConstructor;
import org.arquitech.authservice.Authentication.domain.model.entity.Role;
import org.arquitech.authservice.Authentication.domain.model.entity.User;
import org.arquitech.authservice.Authentication.domain.persistence.UserRepository;
import org.arquitech.authservice.Authentication.jwt.JwtService;
import org.arquitech.authservice.Authentication.resource.AuthResponse;
import org.arquitech.authservice.Authentication.resource.ChangePasswordRequest;
import org.arquitech.authservice.Authentication.resource.LoginRequest;
import org.arquitech.authservice.Authentication.resource.RegisterRequest;
import org.arquitech.authservice.Shared.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDetails user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

            String token = jwtService.getToken(user);
            return AuthResponse.builder().token(token).build();
        } catch (Exception ex) {
            throw new CustomException("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    public AuthResponse changePassword(ChangePasswordRequest request) {

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new CustomException("New passwords do not match", HttpStatus.BAD_REQUEST);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        userRepository.findById(((User) user).getId())
                .ifPresent(userToUpdate -> {
                    userToUpdate.setPassword(passwordEncoder.encode(request.getNewPassword()));
                    userRepository.save(userToUpdate);
                });

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .gymName(request.getGymName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .city(request.getCity())
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

    public Integer registerClient(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .gymName(request.getGymName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .city(request.getCity())
                .role(Role.USER)
                .build();



         userRepository.save(user);/*AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();*/

        return user.getId();
    }

    public boolean findUserById(Integer id) {
        return userRepository.existsById(id);
    }

}
