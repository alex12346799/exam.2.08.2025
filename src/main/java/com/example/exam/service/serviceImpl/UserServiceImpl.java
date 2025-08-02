package com.example.exam.service.serviceImpl;

import com.example.exam.dao.UserDao;
import com.example.exam.dto.UserRequestDto;
import com.example.exam.dto.UserResponseDto;
import com.example.exam.exceptions.NotFoundException;
import com.example.exam.mapper.UserMapper;
import com.example.exam.model.User;
import com.example.exam.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private  final PasswordEncoder encoder;

    @Override
    public void registerUser(UserRequestDto dto) {
        if (dto.getPhone() == null || dto.getPhone().isBlank()) {
            throw new NotFoundException("Телефон не должен быть пустым");
        }
        if (userDao.findByPhone(dto.getPhone()).isPresent()) {
            throw new NotFoundException("Пользователь с таким телефоном уже существует");
        }
        User user = UserMapper.fromUserRequestDto(dto);
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("USER");
        user.setEnabled(true);
        userDao.save(user);
    }

    @Override
    public UserResponseDto getUserByPhone(String phone) {
        return userDao.findByPhone(phone)
                .map(UserMapper::toUserResponseDto)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }

    @Override
    public UserResponseDto getUserById(int id) {
        return userDao.findById(id)
                .map(UserMapper::toUserResponseDto)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }
}
