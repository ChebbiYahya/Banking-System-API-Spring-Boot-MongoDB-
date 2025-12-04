package com.bank.crudbankwithmongodb.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountResponseDTO {
    private String id;
    private String iban;
    private BigDecimal balance;
    private String status;
    private String accountType;
    private LocalDateTime createdAt;

    private String customerId;
    private String customerFullName;
}
