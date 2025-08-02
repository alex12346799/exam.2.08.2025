package com.example.exam.mapper;

import com.example.exam.dto.AccountRequestDto;
import com.example.exam.dto.AccountResponseDto;
import com.example.exam.model.Account;

public class AccountMapper {
    public static Account fronAccountRequestDto(AccountRequestDto dto) {
        if (dto == null) return null;
        Account account = new Account();
        account.setCurrency(dto.getCurrency());
        return account;
    }
    public static AccountResponseDto toAccountResponseDto(Account account) {
        if (account == null) return null;
        AccountResponseDto dto = new AccountResponseDto();
        dto.setId(account.getId());
        dto.setCurrency(account.getCurrency());
        dto.setBalance(account.getBalance());
        dto.setAccountNumber(account.getAccountNumber());
        return dto;
    }
}
