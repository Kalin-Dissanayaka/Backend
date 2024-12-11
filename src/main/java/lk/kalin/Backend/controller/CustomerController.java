package lk.kalin.Backend.controller;

import lk.kalin.Backend.entity.Customer;
import lk.kalin.Backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*", allowedHeaders="*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/getAllCustomer")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
