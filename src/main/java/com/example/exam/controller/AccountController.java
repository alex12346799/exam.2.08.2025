
package com.example.exam.controller;

import com.example.exam.dto.AccountRequestDto;
import com.example.exam.dto.AccountResponseDto;
import com.example.exam.model.Account;
import com.example.exam.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public AccountResponseDto createAccount(@RequestBody @Valid AccountRequestDto dto, Authentication auth) {
        String username = auth.getName();
        int userId = accountService.getUserIdByUsername(username);
        return accountService.createAccount(userId, dto);
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam String number) {
        Account account = accountService.getAccountByNumber(number);
        return account.getBalance();
    }

    @PostMapping("/balance")
    public void setBalance(@RequestParam String number, @RequestParam double amount) {
        Account account = accountService.getAccountByNumber(number);
        double newBalance = account.getBalance() + amount;
        accountService.updateBalance(account, newBalance);
    }
}

