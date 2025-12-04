package com.bank.crudbankwithmongodb.repository;

import com.bank.crudbankwithmongodb.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    List<Transaction> findByBankAccountIdOrderByCreatedAtDesc(String bankAccountId);
}
