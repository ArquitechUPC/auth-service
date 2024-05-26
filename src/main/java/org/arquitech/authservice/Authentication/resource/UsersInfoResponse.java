package org.arquitech.authservice.Authentication.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersInfoResponse {
    String username;
    String email;
    String phoneNumber;
    Integer companyId;
}
