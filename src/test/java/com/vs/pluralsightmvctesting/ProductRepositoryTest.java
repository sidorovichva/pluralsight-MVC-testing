package com.vs.pluralsightmvctesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Product Repository should")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void test100() {
        //Product product1 = productRepository.save(new Product("Phone", 10));
        Product product1 = testEntityManager.persistFlushFind(new Product("Phone", 10));

        Product product2 = productRepository.findById(1).get();

        assertNotNull(product2.getId());
        assertEquals(product1.getName(), product2.getName());
    }
}