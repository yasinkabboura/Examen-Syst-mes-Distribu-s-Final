package com.example.inventorycommandservice.controllers;

import coreapi.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping("/commands")
@CrossOrigin("*")
public class ProductCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    public ProductCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }
    @GetMapping("/eventStoress/{id}")
    public Stream streamEvents(@PathVariable String id){
        return eventStore.readEvents(id).asStream();
    }
    @PostMapping("/product/toCategory")
    public CompletableFuture<String> addNewProductCommand(@RequestBody ProductDTO request){
        String id = UUID.randomUUID().toString();
        return this.commandGateway.send(new CreateNewProductCommand(
                id,
                request
        ));
    }


}
