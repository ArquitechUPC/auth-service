package org.arquitech.authservice.Authentication.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.arquitech.authservice.Authentication.domain.model.entity.User;
import org.arquitech.authservice.Authentication.resource.AuthResponse;
import org.arquitech.authservice.Authentication.resource.ChangePasswordRequest;
import org.arquitech.authservice.Authentication.resource.LoginRequest;
import org.arquitech.authservice.Authentication.resource.RegisterRequest;
import org.arquitech.authservice.Authentication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "auth", description = "the auth API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Login", description = "Login to the application")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Operation(summary = "Change password", description = "Change password")
    @PostMapping("/change-password")
    public ResponseEntity<AuthResponse> login(@RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(authService.changePassword(request));
    }

    @Operation(summary = "Register", description = "Register to the application")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
