package com.example.exam.service.serviceImpl;

import com.example.exam.dao.AccountDao;
import com.example.exam.dao.TransactionDao;
import com.example.exam.dto.TransactionRequestDto;
import com.example.exam.dto.TransactionResponseDto;
import com.example.exam.exceptions.NotFoundException;
import com.example.exam.mapper.TransactionMapper;
import com.example.exam.model.Account;
import com.example.exam.model.Transaction;
import com.example.exam.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto, int userId) throws Exception {
        if (transactionRequestDto.getAmount() < 0) {
            throw new NumberFormatException("Сумма должна быть больше 0");
        }
        Account sender = accountDao.findById(transactionRequestDto.getSenderAccountId())
                .orElseThrow(() -> new NotFoundException("Счет отправителя не найден"));
        Account receiver = accountDao.findById(transactionRequestDto.getReceiverAccountId())
                .orElseThrow(() -> new NotFoundException("Счет получателя не найден"));
        if (sender.getBalance() < transactionRequestDto.getAmount()) {
            throw new NotFoundException("Недостаточно средств на счете отправителя");
        }
        Transaction transaction = TransactionMapper.fromTransactionRequestDto(transactionRequestDto);
        transaction.setStatus("PENDING");
        transaction.setApprovalRequired(transactionRequestDto.getAmount()>10);
        transaction.setApproved(!transaction.isApprovalRequired());
        if (!transaction.isApprovalRequired()){
            sender.setBalance(sender.getBalance() - transactionRequestDto.getAmount());
            receiver.setBalance(receiver.getBalance() + transactionRequestDto.getAmount());
            accountDao.updateBalance(sender);
            accountDao.updateBalance(receiver);
            transaction.setStatus("SUCCESS");
        }
        transactionDao.save(transaction);
        return TransactionMapper.toTransactionResponseDto(transaction);
    }


    @Override
    public List<TransactionResponseDto> getTransactionHistory(int accountId, int userId) {
        return transactionDao.findByAccountId(accountId).stream()
                .map(TransactionMapper::toTransactionResponseDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<TransactionResponseDto> getAll() {
        return transactionDao.findAll().stream()
                .map(TransactionMapper::toTransactionResponseDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<TransactionResponseDto> getAllApprovalRequired() {
        return transactionDao.findApprovalRequired().stream()
                .map(TransactionMapper::toTransactionResponseDto)
                .collect(Collectors.toList());
    }
    @Override
    public Transaction getByIdOrThrow(int id) {
        return transactionDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Транзакция не найдена"));
    }
}
