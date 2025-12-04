package com.bank.crudbankwithmongodb.services;

import com.bank.crudbankwithmongodb.entities.Customer;
import com.bank.crudbankwithmongodb.exceptions.CustomerNotFoundException;
import com.bank.crudbankwithmongodb.interfaces.CustomerService;
import com.bank.crudbankwithmongodb.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServicesImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        customer.setId(null); // Mongo génère l'id
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(String id, Customer customer) {
        Customer existing = getCustomerById(id);
        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setEmail(customer.getEmail());
        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(String id) {
        Customer existing = getCustomerById(id);
        customerRepository.delete(existing);
    }
}
