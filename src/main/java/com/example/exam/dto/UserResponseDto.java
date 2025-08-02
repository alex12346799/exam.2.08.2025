package com.example.exam.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private int id;
    private String phone;
    private String username;
    private String role;
    private boolean enabled;
}
