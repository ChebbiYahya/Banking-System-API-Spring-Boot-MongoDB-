package com.bank.crudbankwithmongodb.mappers;

import com.bank.crudbankwithmongodb.DTO.TransactionResponseDTO;
import com.bank.crudbankwithmongodb.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionResponseDTO toResponseDTO(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId()) // String
                .type(transaction.getType().name())
                .amount(transaction.getAmount())
                .description(transaction.getDescription())
                .createdAt(transaction.getCreatedAt())
                .balanceAfterOperation(transaction.getBalanceAfterOperation())
                .build();
    }
}
