package com.example.inventoryqueryservice.entities;


import coreapi.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productId;
    private String nom;
    private double prix;
    private int quntity;
    private ProductStatus status;
    @ManyToOne
    private Category category;

}
