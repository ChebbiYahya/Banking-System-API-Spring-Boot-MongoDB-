package com.bank.crudbankwithmongodb.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    private String id; // <--- String

    private BigDecimal amount;

    private TransactionType type; // DEPOSIT / WITHDRAW

    private String description;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private BigDecimal balanceAfterOperation;

    // au lieu de ManyToOne BankAccount, on stocke juste l'id du compte
    private String bankAccountId;
}
