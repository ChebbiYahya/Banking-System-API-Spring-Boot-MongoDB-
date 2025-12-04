package com.bank.crudbankwithmongodb.services;

import com.bank.crudbankwithmongodb.entities.Transaction;
import com.bank.crudbankwithmongodb.interfaces.TransactionService;
import com.bank.crudbankwithmongodb.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServicesImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactionsByAccountId(String accountId) {
        return transactionRepository.findByBankAccountIdOrderByCreatedAtDesc(accountId);
    }
}
