package com.example.exam.dto;

import lombok.Data;

@Data
public class AccountResponseDto {
    private int id;
    private String currency;
    private double balance;
    private String accountNumber;
}
