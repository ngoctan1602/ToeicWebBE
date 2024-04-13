package com.tantan.ToeicWeb.controller;

import com.tantan.ToeicWeb.auth.AuthResponse;
import com.tantan.ToeicWeb.auth.UserResponse;
import com.tantan.ToeicWeb.auth.entities.Account;
import com.tantan.ToeicWeb.auth.entities.RefreshToken;
import com.tantan.ToeicWeb.auth.request.LoginRequest;
import com.tantan.ToeicWeb.auth.request.RefreshTokenRequest;
import com.tantan.ToeicWeb.auth.request.RegisterRequest;
import com.tantan.ToeicWeb.auth.services.AuthenServices;
import com.tantan.ToeicWeb.auth.services.JWTServices;
import com.tantan.ToeicWeb.auth.services.RefreshTokenServices;
import com.tantan.ToeicWeb.common.GetIdUser;
import com.tantan.ToeicWeb.entity.User;
import com.tantan.ToeicWeb.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final AuthenServices authService;
    private final RefreshTokenServices refreshTokenService;
    private final JWTServices jwtService;

    public AuthController(AuthenServices authService, RefreshTokenServices refreshTokenService, JWTServices jwtService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        Account account = refreshToken.getAccount();
        String accessToken = jwtService.generateToken(account);
        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build());
    }

    @GetMapping("/profile")
//    @PreAuthorize()
    public UserResponse getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) authentication.getPrincipal();
        User user = account.getUser();
        return new UserResponse(user.getName(), account.getEmail());
    }
}
