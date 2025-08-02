package com.example.exam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountRequestDto {
    @NotBlank(message = "Валюта обязательна")
    private String currency;
}
