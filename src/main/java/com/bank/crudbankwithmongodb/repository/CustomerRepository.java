package com.bank.crudbankwithmongodb.repository;

import com.bank.crudbankwithmongodb.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
