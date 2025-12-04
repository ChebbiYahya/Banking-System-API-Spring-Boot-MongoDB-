package com.bank.crudbankwithmongodb.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountRequestDTO {
    private String iban;
    private String status;
    private String accountType;
    private String customerId;
}
