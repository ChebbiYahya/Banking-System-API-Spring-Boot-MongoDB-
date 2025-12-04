package com.bank.crudbankwithmongodb.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
}
