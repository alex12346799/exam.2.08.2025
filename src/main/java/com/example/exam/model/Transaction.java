package com.example.exam.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private int id;
    private double amount;
    private String status;
    private boolean approvalRequired;
    private boolean approved;
    private LocalDateTime createdAt;

}
