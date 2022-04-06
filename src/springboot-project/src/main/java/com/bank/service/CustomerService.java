package com.bank.service;

import com.bank.entity.Customer;
import lombok.RequiredArgsConstructor;
import com.bank.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer getCustomerInfo(Integer acctID) {
        return customerRepository.findById(acctID).orElse(null);
    }

    public void deleteCustomer(Integer acctID) {
        customerRepository.deleteById(acctID);
    }

}
