package com.example.exam.controller;

import com.example.exam.dto.TransactionRequestDto;
import com.example.exam.dto.TransactionResponseDto;
import com.example.exam.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trabsactions")
public class TransactionController {
    private final TransactionService transactionService;
    @PostMapping
    public TransactionResponseDto createTransaction(@RequestBody TransactionRequestDto dto, Authentication auth) throws Exception {
        int userId = Integer.parseInt(auth.getName());
        return transactionService.createTransaction(dto,userId);
    }
    @GetMapping("/{accountId}/history")
    public List<TransactionResponseDto> getTransactionHistory(@PathVariable int accountId,
                                                              Authentication auth) {
        int userId = Integer.parseInt(auth.getName());
        return transactionService.getTransactionHistory(accountId, userId);
    }
}
