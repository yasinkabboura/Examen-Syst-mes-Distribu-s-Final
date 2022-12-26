package com.example.inventoryqueryservice.service;


import com.example.inventoryqueryservice.entities.Category;
import com.example.inventoryqueryservice.entities.Product;
import com.example.inventoryqueryservice.mappers.CategoryMapper;
import com.example.inventoryqueryservice.mappers.ProductMapper;
import com.example.inventoryqueryservice.repositories.CategoryRepository;
import com.example.inventoryqueryservice.repositories.ProductRepository;
import coreapi.CategoryCreatedEvent;
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
public class CategoryEventHadlerService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private QueryUpdateEmitter queryUpdateEmitter;


    public CategoryEventHadlerService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, QueryUpdateEmitter queryUpdateEmitter) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(CategoryCreatedEvent event, EventMessage<CategoryCreatedEvent> eventMessage){
        log.info("*************** Query Event handler **************");
        log.info("CategoryCreatedEvent occured");
        Category category=categoryMapper.fromCategoryDTO(event.getPayload());
        category.setCatId(event.getId());
        categoryRepository.save(category);
        EventDataResponseDTO eventDataResponseDTO=new EventDataResponseDTO(
                event.getClass().getSimpleName(),
                event
        );
        queryUpdateEmitter.emit(SubscribeToEventsQuery.class,
                (query)->true, eventDataResponseDTO);

    }


}
