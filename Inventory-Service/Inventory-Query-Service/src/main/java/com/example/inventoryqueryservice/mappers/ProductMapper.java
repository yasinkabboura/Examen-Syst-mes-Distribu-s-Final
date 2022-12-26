package com.example.inventoryqueryservice.mappers;



import com.example.inventoryqueryservice.entities.Product;
import coreapi.ProductDTO;
import coreapi.ProductResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product fromProductDTO(ProductDTO productDTO);
    ProductResponseDTO fromProduct(Product product);


}
