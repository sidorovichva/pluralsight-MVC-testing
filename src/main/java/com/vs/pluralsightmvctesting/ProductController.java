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
    public Product getProduct(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @GetMapping()
    public List<Product> getProduct() {
        return productService.findAll();
    }

    @PostMapping()
    public void getProduct(@RequestBody Product product) {
        productService.save(product);
    }

}
