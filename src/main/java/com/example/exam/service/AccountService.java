package com.example.exam.service;

import com.example.exam.dto.AccountRequestDto;
import com.example.exam.dto.AccountResponseDto;
import com.example.exam.model.Account;

import java.util.List;

public interface AccountService {
    AccountResponseDto createAccount(int userId, AccountRequestDto dto);

    List<AccountResponseDto> getAccountsByUserId(int userId);

    Account getAccountByNumber(String accountNumber);

    void updateBalance(Account account, double balance);
}
