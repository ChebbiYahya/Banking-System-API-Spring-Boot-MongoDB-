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
    private String  id;
    private String ownerName;
    private String iban;
    private BigDecimal balance;
    private String status;
    private LocalDateTime createdAt;
}
