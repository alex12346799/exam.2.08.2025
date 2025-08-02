package com.example.exam.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String phone;
    private String username;
    private String password;
    private String role;
    private boolean enabled;
}
