package com.example.authserver.repository;

import com.example.authserver.entity.OauthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OauthUserRepository extends JpaRepository<OauthUser, Long> {
    Optional<OauthUser> findByUsername(String username);
}