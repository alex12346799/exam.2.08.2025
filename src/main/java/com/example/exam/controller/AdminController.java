package com.example.exam.controller;

import com.example.exam.dto.TransactionResponseDto;
import com.example.exam.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/transactions")
public class AdminController {
    private final TransactionService transactionService;
    @GetMapping
    public List<TransactionResponseDto> getAllTransactions(){
        return transactionService.getAll();
    }
    @GetMapping("/approval")
    public List<TransactionResponseDto> getPendingApprovals() {
        return transactionService.getAllApprovalRequired();
    }
    @PostMapping("/approval")
    public ResponseEntity<String> approveTransaction(@RequestBody Map<String, Integer> request) {
        int id = request.get("id");
        transactionService.approve(id);
        return ResponseEntity.ok("Транзакция одобрена");
    }
    @PostMapping("/rollback")
    public ResponseEntity<?> rollbackTransaction(@RequestBody Map<String, Integer> request) {
        int id = request.get("id");
        try {
            transactionService.rollback(id);
            return ResponseEntity.ok("Транзакция откатана");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> TransactionAsDeleted(@PathVariable int id) {
        transactionService.delete(id);
        return ResponseEntity.ok("Транзакция удалена");
    }
}
