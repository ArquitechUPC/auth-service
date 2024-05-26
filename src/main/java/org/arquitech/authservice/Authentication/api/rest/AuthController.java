package org.arquitech.authservice.Authentication.api.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.arquitech.authservice.Authentication.domain.model.entity.Role;
import org.arquitech.authservice.Authentication.domain.model.entity.User;
import org.arquitech.authservice.Authentication.resource.*;
import org.arquitech.authservice.Authentication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Get user", description = "Get users info")
    @GetMapping("/get-clients-info")
    public ResponseEntity<List<UsersInfoResponse>> get(@RequestParam Integer companyId) {
        return ResponseEntity.ok(authService.getUsersByCompanyIdAndRole(companyId, Role.USER));
    }


}
