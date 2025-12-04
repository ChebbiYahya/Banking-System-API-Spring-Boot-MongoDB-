package com.bank.crudbankwithmongodb.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {
    private String id;  // <--- String
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdAt;
}
