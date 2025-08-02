package com.example.exam.controller;

import com.example.exam.dao.AccountDao;
import com.example.exam.dto.AccountRequestDto;
import com.example.exam.dto.AccountResponseDto;
import com.example.exam.exceptions.NotFoundException;
import com.example.exam.model.Account;
import com.example.exam.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    private final AccountDao accountDao;

    @PostMapping
    private AccountResponseDto createAccount(@RequestBody @Valid AccountRequestDto dto, Authentication auth) {
        int userId = Integer.parseInt(auth.getName());
        return accountService.createAccount(userId, dto);
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam String number, Authentication auth) {
        Account account = accountService.getAccountByNumber(number);
        List<AccountResponseDto> userAccount = accountService.getAccountsByUserId(Integer.parseInt(auth.getName()));
        boolean examinationAccount = userAccount.stream().
                allMatch(a -> a.getAccountNumber()
                        .equals(account.getAccountNumber()));
        if (!examinationAccount) {
            throw new NotFoundException("Нет доступа к этому счету");
        }
        return account.getBalance();
    }

    @PostMapping("/balance")
    public void setBalance(@RequestParam String number,
                           @RequestParam double amount,
                           Authentication auth) {
        Account account = accountService.getAccountByNumber(number);
        int userId = Integer.parseInt(auth.getName());
        List<AccountResponseDto> userAccounts = accountService.getAccountsByUserId(userId);

        boolean ownsAccount = userAccounts.stream()
                .anyMatch(a -> a.getAccountNumber().equals(account.getAccountNumber()));

        if (!ownsAccount) {
            throw new NotFoundException("Нельзя пополнить чужой счет");
        }

        double newBalance = account.getBalance() + amount;
        accountService.updateBalance(account, newBalance);
    }


}
