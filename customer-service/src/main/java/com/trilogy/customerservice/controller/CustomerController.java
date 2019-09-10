package com.trilogy.customerservice.controller;

import com.trilogy.customerservice.exceptions.ResourceNotFoundException;
import com.trilogy.customerservice.model.Customer;
import com.trilogy.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @GetMapping("/id")
    public Customer getCustomerById(@PathVariable(value="id") Long id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "id", id));
    }

    @PutMapping("/id")
    public Customer updateCustomer(@PathVariable(value="id") Long customerId, Customer customerDetails){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "id", customerId))
    }



}
