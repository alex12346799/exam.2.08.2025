package com.example.exam.model;

import lombok.Data;

@Data
public class Account {
    private int id;
    public String currency;
    private double balance;
    private String accountNumber;
}
