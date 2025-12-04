package com.bank.crudbankwithmongodb.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    private String id; // <--- String au lieu de Long

    private String firstName;

    private String lastName;

    private String email;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
