package com.example.inventoryqueryservice.service;



import com.example.inventoryqueryservice.entities.Category;
import com.example.inventoryqueryservice.entities.Product;
import com.example.inventoryqueryservice.mappers.ProductMapper;
import com.example.inventoryqueryservice.repositories.CategoryRepository;
import com.example.inventoryqueryservice.repositories.ProductRepository;
import coreapi.GetAllProductsQuery;
import coreapi.GetProductByCat;
import coreapi.GetProductById;
import coreapi.ProductResponseDTO;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductQueryHandler {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;


    public ProductQueryHandler(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @QueryHandler
    public List<ProductResponseDTO>  handler(GetAllProductsQuery query){
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(product->productMapper.fromProduct(product))
                .collect(Collectors.toList());
    }

    @QueryHandler
    public ProductResponseDTO  handler(GetProductById query){
        Product customer=productRepository.findById(query.getCustomerId())
                .orElseThrow(()->new RuntimeException("Customer Not found"));
        ProductResponseDTO productResponseDTO=productMapper.fromProduct(customer);

        return productResponseDTO;
    }
    @QueryHandler
    public List<ProductResponseDTO>  handler(GetProductByCat query){
        Category category = categoryRepository.findById(query.getCatID()).orElseThrow(()->new RuntimeException("Customer Not found"));
        List<Product> products=productRepository.findProductByCategory(category);

        return products.stream().map(product->productMapper.fromProduct(product))
                .collect(Collectors.toList());
    }


}
