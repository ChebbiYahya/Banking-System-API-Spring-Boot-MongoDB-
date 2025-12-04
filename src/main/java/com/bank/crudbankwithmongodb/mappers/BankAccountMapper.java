package com.bank.crudbankwithmongodb.mappers;


import com.bank.crudbankwithmongodb.DTO.BankAccountRequestDTO;
import com.bank.crudbankwithmongodb.DTO.BankAccountResponseDTO;
import com.bank.crudbankwithmongodb.entities.BankAccount;
import com.bank.crudbankwithmongodb.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public BankAccount toEntity(BankAccountRequestDTO dto) {
        return BankAccount.builder()
                .iban(dto.getIban())
                .status(dto.getStatus())
                .accountType(dto.getAccountType())
                .build();
        // le Customer sera injecté dans le service (avec le @DBRef)
    }

    public BankAccountResponseDTO toResponseDTO(BankAccount account) {
        Customer customer = account.getCustomer();
        String fullName = customer != null
                ? customer.getFirstName() + " " + customer.getLastName()
                : null;

        return BankAccountResponseDTO.builder()
                .id(account.getId()) // String
                .iban(account.getIban())
                .balance(account.getBalance())
                .status(account.getStatus())
                .accountType(account.getAccountType())
                .createdAt(account.getCreatedAt())
                .customerId(customer != null ? customer.getId() : null)
                .customerFullName(fullName)
                .build();
    }
}
