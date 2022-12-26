package com.example.inventoryqueryservice.controllers;

import coreapi.GetAllProductsQuery;
import coreapi.GetProductByCat;
import coreapi.GetProductById;
import coreapi.ProductResponseDTO;
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
public class ProductQueryController {
    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/products/all")
    public CompletableFuture<List<ProductResponseDTO>> allProducts(){
        return queryGateway.query(
                new GetAllProductsQuery()
                , ResponseTypes.multipleInstancesOf(ProductResponseDTO.class)
        );
    }

    @GetMapping("/product/byId/{id}")
    public CompletableFuture<ProductResponseDTO> geProductById(@PathVariable String id){
        return queryGateway.query(
                new GetProductById(id)
                , ResponseTypes.instanceOf(ProductResponseDTO.class)
        );
    }
    @GetMapping("/product/byCat/{cat}")
    public CompletableFuture<List<ProductResponseDTO>> geProductByCat(@PathVariable String cat){
        return queryGateway.query(
                new GetProductByCat(cat)
                , ResponseTypes.multipleInstancesOf(ProductResponseDTO.class)
        );
    }

}
