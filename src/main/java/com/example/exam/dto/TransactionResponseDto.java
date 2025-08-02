package com.example.exam.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TransactionResponseDto {
    private int id;
    private double amount;
    private String status;
    private boolean approvalRequired;
    private boolean approved;
    private LocalDateTime createdAt;
}
