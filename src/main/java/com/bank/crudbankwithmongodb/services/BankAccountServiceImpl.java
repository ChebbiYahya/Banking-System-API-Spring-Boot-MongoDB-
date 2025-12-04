package com.bank.crudbankwithmongodb.services;


import com.bank.crudbankwithmongodb.entities.BankAccount;
import com.bank.crudbankwithmongodb.entities.Customer;
import com.bank.crudbankwithmongodb.entities.Transaction;
import com.bank.crudbankwithmongodb.entities.TransactionType;
import com.bank.crudbankwithmongodb.exceptions.AccountNotFoundException;
import com.bank.crudbankwithmongodb.exceptions.CustomerNotFoundException;
import com.bank.crudbankwithmongodb.exceptions.InsufficientBalanceException;
import com.bank.crudbankwithmongodb.interfaces.BankAccountService;
import com.bank.crudbankwithmongodb.repository.BankAccountRepository;
import com.bank.crudbankwithmongodb.repository.CustomerRepository;
import com.bank.crudbankwithmongodb.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

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
    public BankAccount createAccount(String customerId, BankAccount account) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

        if (account.getCreatedAt() == null) {
            account.setCreatedAt(LocalDateTime.now());
        }
        if (account.getBalance() == null) {
            account.setBalance(BigDecimal.ZERO);
        }
        if (account.getStatus() == null) {
            account.setStatus("ACTIVE");
        }
        if (account.getAccountType() == null) {
            account.setAccountType("CHECKING");
        }

        account.setId(null); // Mongo va générer l'ObjectId
        account.setCustomer(customer);

        return bankAccountRepository.save(account);
    }

    @Override
    public BankAccount updateAccount(String id, BankAccount account) {
        BankAccount existing = getAccountById(id);
        existing.setIban(account.getIban());
        existing.setStatus(account.getStatus());
        existing.setAccountType(account.getAccountType());
        return bankAccountRepository.save(existing);
    }

    @Override
    public void deleteAccount(String id) {
        BankAccount existing = getAccountById(id);
        bankAccountRepository.delete(existing);
        // Optionnel : supprimer aussi les transactions liées
        // transactionRepository.deleteAll(transactionRepository.findByBankAccountIdOrderByCreatedAtDesc(id));
    }

    @Override
    public BankAccount deposit(String id, BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        BankAccount account = getAccountById(id);
        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);

        BankAccount saved = bankAccountRepository.save(account);

        createTransaction(saved, amount, TransactionType.DEPOSIT, "Deposit");

        return saved;
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

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);

        BankAccount saved = bankAccountRepository.save(account);

        createTransaction(saved, amount, TransactionType.WITHDRAW, "Withdraw");

        return saved;
    }

    private void createTransaction(BankAccount account,
                                   BigDecimal amount,
                                   TransactionType type,
                                   String description) {

        Transaction transaction = Transaction.builder()
                .amount(amount)
                .type(type)
                .description(description)
                .createdAt(LocalDateTime.now())
                .balanceAfterOperation(account.getBalance())
                .bankAccountId(account.getId()) // <--- important
                .build();

        transactionRepository.save(transaction);
    }
}