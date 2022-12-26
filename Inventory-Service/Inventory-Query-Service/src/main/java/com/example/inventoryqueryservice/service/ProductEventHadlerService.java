package com.example.inventoryqueryservice.service;


import com.example.inventoryqueryservice.entities.Category;
import com.example.inventoryqueryservice.entities.Product;
import com.example.inventoryqueryservice.mappers.ProductMapper;
import com.example.inventoryqueryservice.repositories.CategoryRepository;
import com.example.inventoryqueryservice.repositories.ProductRepository;
import coreapi.EventDataResponseDTO;
import coreapi.ProductCreatedEvent;
import coreapi.SubscribeToEventsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class ProductEventHadlerService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;
    private QueryUpdateEmitter queryUpdateEmitter;


    public ProductEventHadlerService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, QueryUpdateEmitter queryUpdateEmitter) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(ProductCreatedEvent event, EventMessage<ProductCreatedEvent> eventMessage){
        log.info("*************** Query Event handler **************");
        log.info("ProductCreatedEvent occured");
        Product product=productMapper.fromProductDTO(event.getPayload());
        product.setProductId(event.getId());
        Category category = categoryRepository.findById(event.getPayload().getCatID()).orElse(null);
        product.setCategory(category);
        productRepository.save(product);
        category.getProducts().add(product);
        EventDataResponseDTO eventDataResponseDTO=new EventDataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class,
                (query)->true, eventDataResponseDTO);

    }


}
