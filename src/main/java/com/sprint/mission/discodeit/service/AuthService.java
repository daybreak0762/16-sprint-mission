package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.dto.UserResponseDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.util.NoSuchElementException;

public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new NoSuchElementException("Wrong password");
        }

        UserStatus userStatus = new UserStatus(user.getId());
        UserResponseDto userResponseDto =
                new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getProfileId(), userStatus.isOnline());

        return userResponseDto;
    }
}
