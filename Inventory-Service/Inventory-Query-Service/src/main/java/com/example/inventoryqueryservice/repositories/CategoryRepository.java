package com.example.inventoryqueryservice.repositories;


import com.example.inventoryqueryservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
