package com.example.exam.dao;

import com.example.exam.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountDao {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Account> rowMapper = (rs, rowNum) -> {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setCurrency(rs.getString("currency"));
        account.setBalance(rs.getDouble("balance"));
        account.setAccountNumber(rs.getString("account_number"));
        return account;
    };

    public void save(Account account, int userId) {
        String sql = "INSERT INTO accounts (user_id, currency, balance, account_number) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, userId, account.getCurrency(), account.getBalance(), account.getAccountNumber());
    }

    public List<Account> findByUserId(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id = ?";
        return jdbcTemplate.query(sql, rowMapper, userId);
    }

    public Optional<Account> findByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        List<Account> accounts = jdbcTemplate.query(sql, rowMapper, accountNumber);
        return accounts.stream().findFirst();
    }

    public Optional<Account> findById(int id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        List<Account> accounts = jdbcTemplate.query(sql, rowMapper, id);
        return accounts.stream().findFirst();
    }

    public void updateBalance(Account account) {
        String sql = "UPDATE accounts SET balance = ? WHERE id = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getId());
    }
}
