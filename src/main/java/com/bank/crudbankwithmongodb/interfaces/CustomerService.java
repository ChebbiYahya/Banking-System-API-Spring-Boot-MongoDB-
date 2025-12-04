package com.bank.crudbankwithmongodb.interfaces;

import com.bank.crudbankwithmongodb.entities.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getCustomerById(String id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(String id, Customer customer);
    void deleteCustomer(String id);
}
