package com.bank.crudbankwithmongodb.mappers;

import com.bank.crudbankwithmongodb.DTO.CustomerRequestDTO;
import com.bank.crudbankwithmongodb.DTO.CustomerResponseDTO;
import com.bank.crudbankwithmongodb.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toEntity(CustomerRequestDTO dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .build();
    }

    public CustomerResponseDTO toResponseDTO(Customer customer) {
        return CustomerResponseDTO.builder()
                .id(customer.getId()) // String
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .createdAt(customer.getCreatedAt())
                .build();
    }
}
