package com.example.exam.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionRollbackDao {
    private final JdbcTemplate jdbcTemplate;
    public void saveRollback(int transactionId, String reason) {
        String sql = "INSERT INTO transaction_rollback (transaction_id, reason) VALUES (?, ?)";
        jdbcTemplate.update(sql, transactionId, reason);
    }
    public boolean existsByTransactionId(int transactionId) {
        String sql = "SELECT COUNT(*) FROM transaction_rollback WHERE transaction_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, transactionId);
        return count != null && count > 0;
    }

}
