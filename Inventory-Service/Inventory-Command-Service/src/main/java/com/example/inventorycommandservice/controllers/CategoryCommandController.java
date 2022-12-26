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
public class CategoryCommandController {
    private CommandGateway commandGateway;
    private EventStore eventStore;

    public CategoryCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }
    @PostMapping("/category/create")
    public CompletableFuture<String> addNewCategoryCommand(@RequestBody CategoryDTO request){
        String id = UUID.randomUUID().toString();
        return this.commandGateway.send(new CreateNewCategoryCommand(
                id,
                request
        ));
    }
    @GetMapping("/eventStores/{id}")
    public Stream streamEvents(@PathVariable String id){
        return eventStore.readEvents(id).asStream();
    }

    }

