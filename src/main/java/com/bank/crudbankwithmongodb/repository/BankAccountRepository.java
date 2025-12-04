package com.bank.crudbankwithmongodb.repository;

import com.bank.crudbankwithmongodb.entities.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {
    Optional<BankAccount> findByIban(String iban);
}
