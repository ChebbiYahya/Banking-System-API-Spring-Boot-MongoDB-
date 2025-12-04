package com.bank.crudbankwithmongodb.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "bank_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {
    @Id
    private String id; // <--- String

    private String iban;

    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @Builder.Default
    private String status = "ACTIVE";

    @Builder.Default
    private String accountType = "CHECKING";

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @DBRef
    private Customer customer; // <--- lien vers Customer

}
