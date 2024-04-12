package com.tantan.ToeicWeb.auth.repository;

import com.tantan.ToeicWeb.auth.entities.RefreshToken;
import com.tantan.ToeicWeb.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
