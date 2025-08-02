package com.example.exam.dao;

import com.example.exam.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setPhone(rs.getString("phone"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setEnabled(rs.getBoolean("enabled"));
        return user;
    };

    public void save(User user) {
        String sql = "INSERT INTO users (phone, username, password, role, enabled) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getPhone(), user.getUsername(), user.getPassword(), user.getRole(), user.isEnabled());
    }

    public Optional<User> findByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone = ?";
        List<User> users = jdbcTemplate.query(sql, rowMapper, phone);
        return users.stream().findFirst();
    }

    public Optional<User> findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, rowMapper, id);
        return users.stream().findFirst();
    }
    public Optional<Integer> findUserIdByUsername(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"), username)
                .stream()
                .findFirst();
    }





}
