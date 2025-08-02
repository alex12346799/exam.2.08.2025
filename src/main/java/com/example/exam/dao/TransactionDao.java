package com.example.exam.dao;

import com.example.exam.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class TransactionDao {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Transaction> rowMapper = (rs, rowNum) -> {
        Transaction t = new Transaction();
        t.setId(rs.getInt("id"));
        t.setSenderAccount(rs.getString("sender_account"));
        t.setReceiverAccount(rs.getString("receiver_account"));
        t.setAmount(rs.getDouble("amount"));
        t.setStatus(rs.getString("status"));
        t.setApprovalRequired(rs.getBoolean("approval_required"));
        t.setApproved(rs.getBoolean("approved"));
        Timestamp timestamp = rs.getTimestamp("created_at");
        if (timestamp != null) {
            t.setCreatedAt(timestamp.toLocalDateTime());
        }
        return t;
    };

    public void save(Transaction transaction) {
        String sql = "INSERT INTO transactions (sender_account, receiver_account, amount, status, approval_required, approved) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                transaction.getSenderAccount(),
                transaction.getReceiverAccount(),
                transaction.getAmount(),
                transaction.getStatus(),
                transaction.isApprovalRequired(),
                transaction.isApproved());
    }

    public List<Transaction> findByAccountId(int accountId) {
        String sql = "SELECT * FROM transactions WHERE sender_account_id = ? OR receiver_account_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, rowMapper, accountId, accountId);
    }

    public List<Transaction> findAll() {
        String sql = "SELECT * FROM transactions ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Transaction> findApprovalRequired() {
        String sql = "SELECT * FROM transactions WHERE approval_required = true AND approved = false ORDER BY created_at ASC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Transaction> findById(int id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        List<Transaction> list = jdbcTemplate.query(sql, rowMapper, id);
        return list.stream().findFirst();
    }

}
