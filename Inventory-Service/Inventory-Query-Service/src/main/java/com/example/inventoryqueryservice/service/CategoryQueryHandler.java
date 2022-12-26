package com.example.inventoryqueryservice.service;



import com.example.inventoryqueryservice.entities.Category;
import com.example.inventoryqueryservice.entities.Product;
import com.example.inventoryqueryservice.mappers.CategoryMapper;
import com.example.inventoryqueryservice.mappers.ProductMapper;
import com.example.inventoryqueryservice.repositories.CategoryRepository;
import com.example.inventoryqueryservice.repositories.ProductRepository;
import coreapi.*;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryQueryHandler {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;


    public CategoryQueryHandler(CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
    }

    @QueryHandler
    public List<CategoryResponseDTO>  handler(GetAllCategoriesQuery query){
        List<Category> allcategories = categoryRepository.findAll();
        return allcategories.stream().map(ca->categoryMapper.fromCategory(ca))
                .collect(Collectors.toList());
    }

    @QueryHandler
    public CategoryResponseDTO  handler(GetCategoryById query){
        Category category=categoryRepository.findById(query.getCustomerId())
                .orElseThrow(()->new RuntimeException("Category Not found"));
        CategoryResponseDTO categoryResponseDTO=categoryMapper.fromCategory(category);

        return categoryResponseDTO;
    }



}
