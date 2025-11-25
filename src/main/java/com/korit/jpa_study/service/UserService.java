package com.korit.jpa_study.service;

import com.korit.jpa_study.dto.Request.AddUserReqDto;
import com.korit.jpa_study.dto.Request.EditUserReqDto;
import com.korit.jpa_study.dto.Response.ApiRespDto;
import com.korit.jpa_study.entity.User;
import com.korit.jpa_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ApiRespDto<?> add(AddUserReqDto addUserReqDto) {
        Optional<User> foundUser = userRepository.findByUsername(addUserReqDto.getUsername());
        if (foundUser.isPresent()) {
            return new ApiRespDto<>("failed", "중복된 username", "null");
        }
        return new ApiRespDto<>("success", "추가완료", userRepository.save(addUserReqDto.toEntity()));
    }

    public ApiRespDto<?> getUserAll() {
        return new ApiRespDto<>("success", "User 전체 조회 완료", userRepository.findAll());
    }

    public ApiRespDto<?> getUserByUserId(Integer userId) {
        Optional<User> foundUser = userRepository.findByUserId(userId);
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않는 user", "null");
        }
        return new ApiRespDto<>("success", "user 조회 완료", userRepository.findByUserId(userId));
    }

    public ApiRespDto<?> getUserByUsername(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않는 user", "null");
        }
        return new ApiRespDto<>("success", "user 조회 완료", foundUser.get());
    }

    public ApiRespDto<?> editUser(EditUserReqDto editUserReqDto) {
        Optional<User> foundUser = userRepository.findByUserId(editUserReqDto.getUserId());
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않는 user", "null");
        }

        User user = foundUser.get();
        if (editUserReqDto.getUsername() != null) { // username이 null이 아닐 때만 변경
            user.setUsername(editUserReqDto.getUsername());
        }
        if (editUserReqDto.getPassword() != null) { // password가 null이 아닐 때만 변경
            user.setPassword(editUserReqDto.getPassword());
        }
        user.setUpdateDt(LocalDateTime.now());
        return new ApiRespDto<>("success", "user 수정 완료", userRepository.save(user));
    }

    public ApiRespDto<?> removeUser(Integer userId) {
        Optional<User> foundUser = userRepository.findByUserId(userId);
        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않는 user", "null");
        }
        userRepository.deleteById(userId);
        return new ApiRespDto<>("success", "user 삭제 완료", null);
    }
}
