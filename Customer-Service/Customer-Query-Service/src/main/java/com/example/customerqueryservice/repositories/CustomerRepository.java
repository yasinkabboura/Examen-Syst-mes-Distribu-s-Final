package com.example.customerqueryservice.repositories;

import com.example.customerqueryservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
