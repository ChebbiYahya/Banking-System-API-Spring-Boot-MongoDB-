package com.bank.crudbankwithmongodb.interfaces;

import com.bank.crudbankwithmongodb.entities.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByAccountId(String accountId);
}
