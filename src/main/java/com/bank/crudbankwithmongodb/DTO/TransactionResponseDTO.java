package com.bank.crudbankwithmongodb.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDTO {
    private String id; // <--- String
    private String type;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private BigDecimal balanceAfterOperation;
}
