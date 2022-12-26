package com.example.inventoryqueryservice.mappers;



import com.example.inventoryqueryservice.entities.Category;
import com.example.inventoryqueryservice.entities.Product;
import coreapi.CategoryDTO;
import coreapi.CategoryResponseDTO;
import coreapi.ProductDTO;
import coreapi.ProductResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category fromCategoryDTO(CategoryDTO categoryDTO);
    CategoryResponseDTO fromCategory(Category category);


}
