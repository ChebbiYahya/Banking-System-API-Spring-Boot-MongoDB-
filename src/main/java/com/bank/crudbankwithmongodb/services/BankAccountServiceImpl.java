package com.bank.crudbankwithmongodb.services;


import com.bank.crudbankwithmongodb.entities.BankAccount;
import com.bank.crudbankwithmongodb.exceptions.AccountNotFoundException;
import com.bank.crudbankwithmongodb.exceptions.InsufficientBalanceException;
import com.bank.crudbankwithmongodb.interfaces.BankAccountService;
import com.bank.crudbankwithmongodb.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getAccountById(String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
    }

    @Override
    public BankAccount createAccount(BankAccount account) {
        if (account.getCreatedAt() == null) {
            account.setCreatedAt(LocalDateTime.now());
        }
        if (account.getBalance() == null) {
            account.setBalance(BigDecimal.ZERO);
        }
        if (account.getStatus() == null) {
            account.setStatus("ACTIVE");
        }
        account.setId(null); // laisse Mongo générer l’ObjectId
        return bankAccountRepository.save(account);
    }

    @Override
    public BankAccount updateAccount(String id, BankAccount account) {
        BankAccount existing = getAccountById(id);
        existing.setOwnerName(account.getOwnerName());
        existing.setIban(account.getIban());
        existing.setStatus(account.getStatus());
        return bankAccountRepository.save(existing);
    }

    @Override
    public void deleteAccount(String id) {
        BankAccount existing = getAccountById(id);
        bankAccountRepository.delete(existing);
    }

    @Override
    public BankAccount deposit(String id, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        BankAccount account = getAccountById(id);
        account.setBalance(account.getBalance().add(amount));
        return bankAccountRepository.save(account);
    }

    @Override
    public BankAccount withdraw(String id, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        BankAccount account = getAccountById(id);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Not enough balance to withdraw");
        }
        account.setBalance(account.getBalance().subtract(amount));
        return bankAccountRepository.save(account);
    }
}