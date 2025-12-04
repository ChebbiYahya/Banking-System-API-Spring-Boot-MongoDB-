package com.bank.crudbankwithmongodb.controllers;

import com.bank.crudbankwithmongodb.DTO.TransactionResponseDTO;
import com.bank.crudbankwithmongodb.entities.Transaction;
import com.bank.crudbankwithmongodb.interfaces.TransactionService;
import com.bank.crudbankwithmongodb.mappers.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts/{accountId}/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping
    public List<TransactionResponseDTO> getTransactions(@PathVariable String accountId) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
        return transactions.stream()
                .map(transactionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
