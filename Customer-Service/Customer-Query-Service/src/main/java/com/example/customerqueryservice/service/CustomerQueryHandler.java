package com.example.customerqueryservice.service;


import com.example.customerqueryservice.entities.Customer;
import com.example.customerqueryservice.mappers.CustomerMappers;
import com.example.customerqueryservice.repositories.CustomerRepository;
import coreapi.*;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerQueryHandler {
    private CustomerRepository customerRepository;
    private CustomerMappers customerMappers;


    public CustomerQueryHandler(CustomerRepository customerRepository, CustomerMappers customerMappers) {
        this.customerRepository = customerRepository;
        this.customerMappers = customerMappers;
    }

    @QueryHandler
    public List<CustomerResponseDTO>  handler(GetAllCustomersQuery query){
        List<Customer> allCustomers = customerRepository.findAll();
        return allCustomers.stream().map(customer->customerMappers.fromCustomer(customer))
                .collect(Collectors.toList());
    }

    @QueryHandler
    public CustomerResponseDTO  handler(GetCustomerById query){
        Customer customer=customerRepository.findById(query.getCustomerId())
                .orElseThrow(()->new RuntimeException("Customer Not found"));
        CustomerResponseDTO customerResponseDTO=customerMappers.fromCustomer(customer);

        return customerResponseDTO;
    }


}
