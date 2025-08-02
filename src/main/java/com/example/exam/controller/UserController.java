package com.example.exam.controller;

import com.example.exam.dto.UserRequestDto;
import com.example.exam.dto.UserResponseDto;
import com.example.exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody UserRequestDto userRequestDto) {
        userService.registerUser(userRequestDto);
    }

    @GetMapping("/user/{phone}")
    public UserResponseDto getUserByPhone(@PathVariable String phone) {
        return userService.getUserByPhone(phone);
    }
}
