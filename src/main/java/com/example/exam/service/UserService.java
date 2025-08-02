package com.example.exam.service;

import com.example.exam.dto.UserRequestDto;
import com.example.exam.dto.UserResponseDto;

import java.util.Optional;

public interface UserService {
    void registerUser(UserRequestDto dto);
    UserResponseDto getUserByPhone(String phone);
    UserResponseDto getUserById(int id);
}
