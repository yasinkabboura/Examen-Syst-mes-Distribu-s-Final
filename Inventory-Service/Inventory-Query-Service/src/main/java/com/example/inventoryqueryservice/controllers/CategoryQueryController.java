package com.example.inventoryqueryservice.controllers;

import coreapi.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequestMapping("/query")
@CrossOrigin("*")
public class CategoryQueryController {
    private QueryGateway queryGateway;

    public CategoryQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/category/all")
    public CompletableFuture<List<CategoryResponseDTO>> allProducts(){
        return queryGateway.query(
                new GetAllCategoriesQuery()
                , ResponseTypes.multipleInstancesOf(CategoryResponseDTO.class)
        );
    }

    @GetMapping("/category/byId/{id}")
    public CompletableFuture<CategoryResponseDTO> geProductById(@PathVariable String id){
        return queryGateway.query(
                new GetCategoryById(id)
                , ResponseTypes.instanceOf(CategoryResponseDTO.class)
        );
    }


}
