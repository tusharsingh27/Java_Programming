package com.bank.controller;

import com.bank.entity.Customer;
import com.bank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public void createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    @GetMapping("/customer/{acctID}")
    public Customer getCustomerInfo(@PathVariable ("acctID") Integer acctID) {
        return customerService.getCustomerInfo(acctID);
    }

    @DeleteMapping("/customer/{acctID}")
    public void deleteCustomer(@PathVariable ("acctID") Integer acctID) {
        customerService.deleteCustomer(acctID);
    }

}


