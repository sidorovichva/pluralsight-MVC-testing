package com.vs.pluralsightmvctesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest //@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE); //mock by default
@DisplayName("Product POJO class")
class ProductTest {

    @Test
    void teat100() {
        Product product = new Product(1, null, 10, 1);
        assertEquals(null, product.getName());
    }
}