package org.arquitech.authservice.Authentication.resource;

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
    String email;
    String password;
    String gymName;
    String phoneNumber;
    String address;
    String city;
}
