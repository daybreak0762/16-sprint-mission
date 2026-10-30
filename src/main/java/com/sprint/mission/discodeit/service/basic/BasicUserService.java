package com.sprint.mission.discodeit.service.basic;

import com.sprint.mission.discodeit.dto.UserRequestDto;
import com.sprint.mission.discodeit.dto.UserResponseDto;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.UserStatus;
import com.sprint.mission.discodeit.repository.BinaryContentRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.UserStatusRepository;
import com.sprint.mission.discodeit.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BasicUserService implements UserService {
    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private BinaryContentRepository binaryContentRepository;

    public BasicUserService(UserRepository userRepository, UserStatusRepository userStatusRepository, BinaryContentRepository binaryContentRepository) {
        this.userRepository = userRepository;
        this.userStatusRepository = userStatusRepository;
        this.binaryContentRepository = binaryContentRepository;
    }

    @Override
    public UserResponseDto create(UserRequestDto userRequestDto) {
        if (userRepository.findByUsername(userRequestDto.username()).isPresent() |
                userRepository.findByEmail(userRequestDto.email()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User(userRequestDto.username(), userRequestDto.email(), userRequestDto.password(), userRequestDto.profileId());
        userRepository.save(user);
        UserStatus userStatus = userStatusRepository.save(new UserStatus(user.getId()));
        UserResponseDto responseDto =
                new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getProfileId(), userStatus.isOnline());
        return responseDto;
    }

    @Override
    public UserResponseDto find(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        UserStatus userStatus = userStatusRepository.save(new UserStatus(user.getId()));
        UserResponseDto responseDto =
                new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getProfileId(), userStatus.isOnline());
        return responseDto;
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> responseDtos = new ArrayList<>();
        for (User user : users) {
            UserStatus userStatus = userStatusRepository.save(new UserStatus(user.getId()));
            UserResponseDto responseDto =
                    new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getProfileId(), userStatus.isOnline());
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    @Override
    public UserResponseDto update(UUID userId, UserRequestDto userRequestDto) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
//        user.update(newUsername, newEmail, newPassword);
//        return userRepository.save(user);
        User  user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        UserStatus userStatus = userStatusRepository.save(new UserStatus(user.getId()));
        user.update(userRequestDto.username(), userRequestDto.email(), userRequestDto.password());
        userRepository.save(user);
        UserResponseDto userResponseDto =
                new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getProfileId(), userStatus.isOnline());
        return userResponseDto;
    }

    @Override
    public void delete(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        // 프로필 이미지가 있을 때만 삭제
        UUID profileId = user.getProfileId();
        if (profileId != null) {
            binaryContentRepository.deleteById(profileId);
        }

        // UserStatus는 해당 User의 ID로 찾아 삭제
        userStatusRepository.findByUserId(userId)
                .ifPresent(status -> userStatusRepository.deleteById(status.getId()));

        userRepository.deleteById(userId);
    }

    @Override
    public UserResponseDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        UserStatus userStatus = userStatusRepository.save(new UserStatus(user.getId()));
        UserResponseDto responseDto =
                new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getProfileId(), userStatus.isOnline());
        return responseDto;
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        UserStatus userStatus = userStatusRepository.save(new UserStatus(user.getId()));
        UserResponseDto responseDto =
                new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getProfileId(), userStatus.isOnline());
        return responseDto;
    }
}
