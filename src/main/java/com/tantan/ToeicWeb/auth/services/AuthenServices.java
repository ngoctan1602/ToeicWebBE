package com.tantan.ToeicWeb.auth.services;

import com.tantan.ToeicWeb.auth.AuthResponse;
import com.tantan.ToeicWeb.auth.entities.User;
import com.tantan.ToeicWeb.auth.entities.UserRole;
import com.tantan.ToeicWeb.auth.repository.UserRepository;
import com.tantan.ToeicWeb.auth.request.LoginRequest;
import com.tantan.ToeicWeb.auth.request.RegisterRequest;
import com.tantan.ToeicWeb.exception.CustomException;
import com.tantan.ToeicWeb.response.DataResponse;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenServices {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JWTServices jwtService;
    private final RefreshTokenServices refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest registerRequest) {
        if (StringUtils.isBlank(registerRequest.getName())) {
            throw new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Name don't null", null));
        }
        boolean b = EmailValidator.getInstance().isValid(registerRequest.getEmail());
        if (!b)
        {
            throw new CustomException(new DataResponse(true, HttpStatus.NOT_FOUND.value(), "Email is not valid", null));
        }
        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .userRole(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);
        var accessToken = jwtService.generateToken(savedUser);
        var refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        var accessToken = jwtService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }
}
