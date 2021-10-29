package com.vs.pluralsightmvctesting;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Product p = productService.save(product);
        return p != null ? ResponseEntity.ok(productService.save(product)) : ResponseEntity.badRequest().body("Error");
    }
}
