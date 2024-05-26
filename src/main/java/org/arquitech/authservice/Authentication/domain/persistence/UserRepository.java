package org.arquitech.authservice.Authentication.domain.persistence;

import org.arquitech.authservice.Authentication.domain.model.entity.Role;
import org.arquitech.authservice.Authentication.domain.model.entity.User;
import org.arquitech.authservice.Authentication.resource.UsersInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<List<User>> findByCompanyIdAndRole(Integer companyId, Role role);
    Optional<User> findByEmail(String email);
}
