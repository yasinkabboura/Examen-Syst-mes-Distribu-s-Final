package com.example.customercommandservice.aggregates;

import coreapi.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class CustomerAggregate {
    @AggregateIdentifier
    private String customerId;
    private String nom;
    private String address;
    private String mail;
    private String tel;

    public CustomerAggregate() {
    }
    @CommandHandler
    public CustomerAggregate(CreateNewCustomerCommand command) {
        log.info("CreateNewCustomerCommand received");
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        log.info("CreateNewCustomerCommand occured");
        this.customerId=event.getId();
        this.nom = event.getPayload().getNom();
        this.address = event.getPayload().getAddress();
        this.mail = event.getPayload().getMail();
        this.tel = event.getPayload().getTel();
    }
    @CommandHandler
    public CustomerAggregate(CustomerEditedEvent command) {
        log.info("EditCustomerCommand received");
        AggregateLifecycle.apply(new CustomerEditedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(EditCustomerCommand event){
        log.info("EditCustomerCommand occured");
        this.customerId=event.getId();
        this.address = event.getPayload().getAddress();
        this.mail = event.getPayload().getMail();
        this.tel = event.getPayload().getTel();
    }



}
