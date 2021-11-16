package com.vs.pluralsightmvctesting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    @NonNull
    private String name;
    private Integer quantity;
    private Integer version;

    public Product(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
