package com.example.inventoryqueryservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private String catId;
    private String nom;
    private String description;
    @OneToMany(mappedBy="category")
    private List<Product> products = new ArrayList<>();
}
