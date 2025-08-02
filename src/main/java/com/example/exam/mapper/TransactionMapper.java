package com.example.exam.mapper;

import com.example.exam.dto.TransactionRequestDto;
import com.example.exam.dto.TransactionResponseDto;
import com.example.exam.model.Transaction;

public class TransactionMapper {
    public static Transaction fromTransactionRequestDto(TransactionRequestDto dto) {
        if (dto == null) return null;
        Transaction transaction = new Transaction();
        transaction.setSenderAccount(dto.getSenderAccount());
        transaction.setReceiverAccount(dto.getReceiverAccount());
        transaction.setAmount(dto.getAmount());
        return transaction;
    }
    public static TransactionResponseDto toTransactionResponseDto(Transaction transaction) {
        if (transaction == null) return null;
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setStatus(transaction.getStatus());
        dto.setApprovalRequired(transaction.isApprovalRequired());
        dto.setApproved(transaction.isApproved());
        dto.setCreatedAt(transaction.getCreatedAt());
        return dto;
    }
}
