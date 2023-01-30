package com.egirra.diplomacloudstorage.security.controller;

import com.egirra.diplomacloudstorage.security.domain.JwtRequest;
import com.egirra.diplomacloudstorage.security.domain.JwtResponse;
import com.egirra.diplomacloudstorage.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader(name = "auth-token") String authToken) {
        authService.logout(authToken);
        return ResponseEntity.status(200).build();
    }
}