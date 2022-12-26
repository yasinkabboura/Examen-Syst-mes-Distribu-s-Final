package com.example.customerqueryservice.mappers;


import com.example.customerqueryservice.entities.Customer;
import coreapi.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMappers {
    Customer fromCustomerDto(CustomerDTO customerDTO);
    CustomerResponseDTO fromCustomer(Customer customer);
    Customer fromCustomerEditDto(EditCustomerDTO customer);

}
