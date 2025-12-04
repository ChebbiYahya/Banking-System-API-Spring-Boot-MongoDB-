package com.bank.crudbankwithmongodb.mappers;


import com.bank.crudbankwithmongodb.DTO.BankAccountRequestDTO;
import com.bank.crudbankwithmongodb.DTO.BankAccountResponseDTO;
import com.bank.crudbankwithmongodb.entities.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccount toEntity(BankAccountRequestDTO dto) {
        return BankAccount.builder()
                .ownerName(dto.getOwnerName())
                .iban(dto.getIban())
                .status(dto.getStatus())
                .build();
    }

    public BankAccountResponseDTO toResponseDTO(BankAccount account) {
        return BankAccountResponseDTO.builder()
                .id(account.getId())
                .ownerName(account.getOwnerName())
                .iban(account.getIban())
                .balance(account.getBalance())
                .status(account.getStatus())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
