package com.example.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "^996\\s\\(\\d{3}\\)\\s\\d{2}-\\d{2}-\\d{2}$", message = "Телефон должен быть в формате 996 (XXX) XX-XX-XX")
    private String phone;

    @NotBlank(message = "Имя пользователя обязательно")
    private String username;

    @NotBlank(message = "Пароль обязателен")
    private String password;
}
