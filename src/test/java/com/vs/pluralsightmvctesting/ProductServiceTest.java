package com.vs.pluralsightmvctesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository repository;

    private Product mockProduct1;
    private Product mockProduct2;
    private Product mockProduct3;

    @BeforeEach
    void setup() {
        mockProduct1 = new Product(1, "Product Name 1", 6, 1);
        mockProduct2 = new Product(2, "Product Name 2", 10, 1);
        mockProduct3 = new Product("Product Name 3", 2 );
    }

    @Test
    void findById() {
        Mockito.doReturn(Optional.of(mockProduct1)).when(repository).findById(1);

        Product returnedProduct = service.findById(1);
        assertSame(returnedProduct, mockProduct1);
    }

    @Test
    void findAll() {
        Mockito.doReturn(Arrays.asList(mockProduct1, mockProduct2)).when(repository).findAll();

        List<Product> products = service.findAll();
        assertEquals(2, products.size());
    }

    @Test
    void addProduct() {
        Mockito.doReturn(mockProduct1).when(repository).save(Mockito.any());

        Product returnedProduct = service.save(mockProduct1);
        assertNotNull(returnedProduct);
        assertEquals(1, returnedProduct.getVersion());

        returnedProduct = service.save(mockProduct3);
        assertNotNull(returnedProduct);
        assertEquals(1, returnedProduct.getVersion());
    }
}