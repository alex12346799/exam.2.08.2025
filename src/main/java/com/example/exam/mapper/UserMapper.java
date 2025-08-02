package com.example.exam.mapper;

import com.example.exam.dto.UserRequestDto;
import com.example.exam.dto.UserResponseDto;
import com.example.exam.model.User;

public class UserMapper {
    public static User fromUserRequestDto(UserRequestDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setPhone(dto.getPhone());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
    public static UserResponseDto toUserResponseDto(User user) {
        if (user == null) return null;
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setPhone(user.getPhone());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        dto.setEnabled(user.isEnabled());
        return dto;
    }
}
