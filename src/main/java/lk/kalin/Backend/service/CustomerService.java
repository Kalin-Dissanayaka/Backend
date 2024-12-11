package lk.kalin.Backend.service;

import lk.kalin.Backend.entity.Customer;
import lk.kalin.Backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    // Save or update a customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Retrieve all customers
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    // Find a customer by ID
    public Optional<Customer> getCustomerById(int id) {

        return customerRepository.findById(id);
    }
}

