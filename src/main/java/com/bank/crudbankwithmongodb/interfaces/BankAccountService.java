package com.bank.crudbankwithmongodb.interfaces;



import com.bank.crudbankwithmongodb.entities.BankAccount;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountService {
    List<BankAccount> getAllAccounts();

    BankAccount getAccountById(String id);

    BankAccount createAccount(String customerId, BankAccount account);

    BankAccount updateAccount(String id, BankAccount account);

    void deleteAccount(String id);

    BankAccount deposit(String id, BigDecimal amount);

    BankAccount withdraw(String id, BigDecimal amount);
}
