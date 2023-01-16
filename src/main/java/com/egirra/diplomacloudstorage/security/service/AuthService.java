package com.egirra.diplomacloudstorage.security.service;

import com.egirra.diplomacloudstorage.security.domain.JwtAuthentication;
import com.egirra.diplomacloudstorage.security.domain.JwtRequest;
import com.egirra.diplomacloudstorage.security.domain.JwtResponse;
import com.egirra.diplomacloudstorage.security.domain.User;
import com.egirra.diplomacloudstorage.security.exception.AuthException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userService.getByUserName(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            return new JwtResponse(accessToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}