package com.egirra.diplomacloudstorage.security.service;

import com.egirra.diplomacloudstorage.security.domain.User;
import com.egirra.diplomacloudstorage.security.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getByUserName(@NonNull String userName) {
        return userRepository.findByUserName(userName);
    }
}