package com.example.exam.service.serviceImpl;

import com.example.exam.dao.AccountDao;
import com.example.exam.dao.UserDao;
import com.example.exam.dto.AccountRequestDto;
import com.example.exam.dto.AccountResponseDto;
import com.example.exam.exceptions.NotFoundException;
import com.example.exam.mapper.AccountMapper;
import com.example.exam.model.Account;
import com.example.exam.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final UserDao userDao;

    @Override
    public AccountResponseDto createAccount(int userId, AccountRequestDto dto) {
        List<Account> existingAccount = accountDao.findByUserId(userId);
        if (existingAccount.size() >= 3) {
            throw new NotFoundException("Максимум разрешено 3 счета на пользователя ");
        }
        Account account = AccountMapper.fronAccountRequestDto(dto);
        account.setBalance(0);
        account.setAccountNumber(UUID.randomUUID().toString());
        accountDao.save(account,userId);

        return AccountMapper.toAccountResponseDto(account);
    }

    @Override
    public List<AccountResponseDto> getAccountsByUserId(int userId) {
        List<Account> accounts = accountDao.findByUserId(userId);
        return accounts.stream()
                .map(AccountMapper::toAccountResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountDao.findByAccountNumber(accountNumber)
                .orElseThrow(()-> new NotFoundException("Счет не найден"));
    }

    @Override
    public void updateBalance(Account account, double balance) {
    if (balance<0){
        throw new NotFoundException("Баланс не может быть отрицательным");
    }
    account.setBalance(balance);
    accountDao.updateBalance(account);
    }
    @Override
    public int getUserIdByUsername(String username) {
        return userDao.findUserIdByUsername(username)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
    }
}

