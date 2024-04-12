package com.tantan.ToeicWeb.auth.services;

import com.tantan.ToeicWeb.auth.entities.RefreshToken;
import com.tantan.ToeicWeb.auth.entities.User;
import com.tantan.ToeicWeb.auth.repository.RefreshTokenRepository;
import com.tantan.ToeicWeb.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class RefreshTokenServices {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServices(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken createRefreshToken(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("Not found user with username" + username));
        RefreshToken refreshToken = user.getRefreshToken();
        if (refreshToken ==null)
        {
            long refreshTokenValidity = 30*1000;
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();
        }
        return refreshToken;
    }
    public RefreshToken verifyRefreshToken(String refreshToken)
    {
        RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new NoSuchElementException("Nor found refresh token"));
        if (refToken.getExpirationTime().compareTo(Instant.now())<0)
        {
            refreshTokenRepository.delete(refToken);
            throw  new RuntimeException("Refresh token is expired");
        }
        return refToken;
    }
}
