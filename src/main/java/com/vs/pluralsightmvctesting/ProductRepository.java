package com.vs.pluralsightmvctesting;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Cacheable(value = "products")
    Optional<Product> findByName(String name);
}
