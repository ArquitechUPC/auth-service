package org.arquitech.authservice.Authentication.resource;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    @NotNull
    String email;
    @NotNull
    String password;
    @NotNull
    Integer companyId;
    String phoneNumber;
    String address;
    String city;
}
