package com.bank.crudbankwithmongodb.controllers;

import com.bank.crudbankwithmongodb.entities.BankAccount;
import com.bank.crudbankwithmongodb.DTO.AmountRequestDTO;
import com.bank.crudbankwithmongodb.DTO.BankAccountRequestDTO;
import com.bank.crudbankwithmongodb.DTO.BankAccountResponseDTO;
import com.bank.crudbankwithmongodb.interfaces.BankAccountService;
import com.bank.crudbankwithmongodb.mappers.BankAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;

    @GetMapping
    public List<BankAccountResponseDTO> getAllAccounts() {
        List<BankAccount> accounts = bankAccountService.getAllAccounts();
        return accounts.stream()
                .map(bankAccountMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BankAccountResponseDTO getAccountById(@PathVariable String id) {
        BankAccount account = bankAccountService.getAccountById(id);
        return bankAccountMapper.toResponseDTO(account);
    }

    @PostMapping
    public ResponseEntity<BankAccountResponseDTO> createAccount(@RequestBody BankAccountRequestDTO requestDTO) {
        BankAccount account = bankAccountMapper.toEntity(requestDTO);
        BankAccount saved = bankAccountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankAccountMapper.toResponseDTO(saved));
    }

    @PutMapping("/{id}")
    public BankAccountResponseDTO updateAccount(@PathVariable String id,
                                                @RequestBody BankAccountRequestDTO requestDTO) {
        BankAccount account = bankAccountMapper.toEntity(requestDTO);
        BankAccount updated = bankAccountService.updateAccount(id, account);
        return bankAccountMapper.toResponseDTO(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        bankAccountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/deposit")
    public BankAccountResponseDTO deposit(@PathVariable String id,
                                          @RequestBody AmountRequestDTO requestDTO) {
        BankAccount account = bankAccountService.deposit(id, requestDTO.getAmount());
        return bankAccountMapper.toResponseDTO(account);
    }

    @PostMapping("/{id}/withdraw")
    public BankAccountResponseDTO withdraw(@PathVariable String id,
                                           @RequestBody AmountRequestDTO requestDTO) {
        BankAccount account = bankAccountService.withdraw(id, requestDTO.getAmount());
        return bankAccountMapper.toResponseDTO(account);
    }
}