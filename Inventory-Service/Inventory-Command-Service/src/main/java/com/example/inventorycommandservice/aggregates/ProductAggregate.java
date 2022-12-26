package com.example.inventorycommandservice.aggregates;

import coreapi.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String nom;
    private double prix;
    private int quntity;
    private ProductStatus status;
    private String catID;

    public ProductAggregate() {
    }
    @CommandHandler
    public ProductAggregate(CreateNewProductCommand command) {
        log.info("CreateNewProductCommand received");
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getPayload()
        ));
    }
    @EventSourcingHandler
    public void on(ProductCreatedEvent event){
        log.info("ProductCreatedEvent occured");
        this.productId=event.getId();
        this.nom = event.getPayload().getNom();
        this.prix = event.getPayload().getPrix();
        this.quntity = event.getPayload().getQuntity();
        this.status = event.getPayload().getStatus();
        this.catID = event.getPayload().getCatID();
    }




}
