package com.example.exam.service;

import com.example.exam.dto.TransactionRequestDto;
import com.example.exam.dto.TransactionResponseDto;
import com.example.exam.model.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto, int userId) throws Exception;
    List<TransactionResponseDto> getTransactionHistory(int accountId, int userId);

    List<TransactionResponseDto> getAll();

    List<TransactionResponseDto> getAllApprovalRequired();

    Transaction getByIdOrThrow(int id);

    void approve(int id);

    void rollback(int id);

    void delete(int id);
}
