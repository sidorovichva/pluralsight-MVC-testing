package com.vs.pluralsightmvctesting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        if (product.getId() == null) product.setVersion(1);
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllEqualsOrMoreThan(Integer x) {
        return productRepository.findAll().stream().filter(y -> y.getQuantity() >= x).collect(Collectors.toList());
    }

    public Product findByName(String name) {
        return productRepository.findByName(name).get();
    }

}
