package com.example.customerqueryservice.service;

import com.example.customerqueryservice.entities.Customer;
import com.example.customerqueryservice.mappers.CustomerMappers;
import com.example.customerqueryservice.repositories.CustomerRepository;
import coreapi.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class CustomerEventHadlerService {
    private CustomerRepository customerRepository;
    private CustomerMappers customerMappers;
    private QueryUpdateEmitter queryUpdateEmitter;


    public CustomerEventHadlerService(CustomerRepository customerRepository,  CustomerMappers customerMappers, QueryUpdateEmitter queryUpdateEmitter) {
        this.customerRepository = customerRepository;
        this.customerMappers = customerMappers;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(CustomerCreatedEvent event, EventMessage<CustomerCreatedEvent> eventMessage){
        log.info("*************** Query Event handler **************");
        log.info("CustomerCreatedEvent occured");
        Customer customer=customerMappers.fromCustomerDto(event.getPayload());
        customer.setCustomerId(event.getId());
        customerRepository.save(customer);
        EventDataResponseDTO eventDataResponseDTO=new EventDataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class,
                (query)->true, eventDataResponseDTO);

    }

    @EventHandler
    public void on(CustomerEditedEvent event){
        log.info("*************** Query Event handler **************");
        log.info("CustomerEditedEvent occured");
        Customer customer=customerRepository.findById(event.getId())
                .orElseThrow(()->new RuntimeException("Customer not found"));
        customer=customerMappers.fromCustomerEditDto(event.getPayload());
        customerRepository.save(customer);
        EventDataResponseDTO eventDataResponseDTO=new EventDataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class,
                (query)->true, eventDataResponseDTO);
    }


}
